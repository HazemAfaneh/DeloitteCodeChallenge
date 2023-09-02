package com.kaizenplus.deloittecodechallenge.ui.screen.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.minimumInteractiveComponentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.hazem.corelayer.model.User
import com.kaizenplus.deloittecodechallenge.ui.screen.dashboard.DashboardActivity
import com.kaizenplus.deloittecodechallenge.ui.theme.DeloitteCodeChallengeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = this
        setContent {
            DeloitteCodeChallengeTheme {
                var isLoading by remember { mutableStateOf(false) }

                collectUIState({
                    it?.let { result ->
                        isLoading = result
                    } ?: run {

                    }
                }, {
                    startActivity(Intent(activity, DashboardActivity::class.java))
                }, {
                    it?.let {
                        Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
                    } ?: run {

                    }
                })
                Surface(
                    modifier = Modifier.wrapContentSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val selectedText = remember { mutableStateOf("Login") }
                    Column {
                        Row {
                            TwoTextsWithUnderline(
                                firstText = "Login",
                                secondText = "Register",
                                selectedText = selectedText.value
                            ) {
                                selectedText.value = it
                            }
                        }
                        Row {
                            if (selectedText.value == "Login") {
                                SignInScreen() {
                                    viewModel.actionTrigger(LoginViewModel.UIAction.Login(it))
//
                                }
                            } else if (selectedText.value == "Register") {
                                SignUpScreen(activity) {

                                    viewModel.actionTrigger(LoginViewModel.UIAction.Register(it))

                                }
                            }
                        }
                    }
                    ProgressBar(isVisible = isLoading)

                }
            }
        }
    }

    private fun collectUIState(
        handleLoading: (isLoading: Boolean?) -> Unit,
        success: () -> Unit,
        failed: (message: String?) -> Unit
    ) {
        lifecycleScope.launch {
            viewModel.uiState.collect {
                it?.let { result ->
                    handleLoading(result.isLoading)
                    if (result.isLoggedIn == true) {
                        success()
                    } else {
                        failed(result.loginErrorMessage)
                    }
                }


            }

        }
    }
}

@Composable
fun ProgressBar(
    isVisible: Boolean
) {
    if (isVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp)
            )
        }
    }
}

@Composable
fun TwoTextsWithUnderline(
    firstText: String,
    secondText: String,
    selectedText: String,
    onTextSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        TextWithUnderline(
            selectedText = selectedText,
            text = firstText,
            onClick = { onTextSelected(firstText) }
        )

        TextWithUnderline(
            selectedText = selectedText,
            text = secondText,
            onClick = { onTextSelected(secondText) }
        )
    }
}

@Composable
fun TextWithUnderline(selectedText: String, text: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(4.dp)
            .wrapContentHeight()
    ) {
        Row {
            Text(
                text = text,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
        Row(horizontalArrangement = Arrangement.Center) {
            if (selectedText == text) {
                Spacer(
                    modifier = Modifier
                        .background(Color.Gray)
                        .height(2.dp)
                        .width(60.dp)
                )
            }
        }
    }
}

@Composable
fun SignInScreen(onSubmit: (user: User) -> Unit) {
    var emailState by remember { mutableStateOf("hazemafaneh@gmail.com") }
    var passwordState by remember { mutableStateOf("3") }


    val focus = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 400.dp)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(Modifier.height(12.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = emailState,
                        label = { Text("Email") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
                            emailState = it
                        },
                        maxLines = 1
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = passwordState,
                        label = { Text("Password") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onSend = {
                                focus.clearFocus(force = true)
                                keyboardController?.hide()
                                if (passwordState.isNotBlank()) {
                                    onSubmit(User(email = emailState, password = passwordState))
                                }
                            }
                        ),
                        onValueChange = {
                            passwordState = it
                        },
                        maxLines = 1
                    )
                }
                Spacer(Modifier.height(8.dp))
                Column(Modifier.padding(horizontal = 16.dp)) {
                    Button(
                        enabled = emailState.isNotBlank() && emailState.contains("@") && emailState.contains(
                            "."
                        ) && passwordState.isNotBlank(),
                        onClick = { onSubmit(User(email = emailState, password = passwordState)) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Continue")
                    }
                }
            }
        }
    }
}

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalMaterial3WindowSizeClassApi::class
)
@Composable
fun SignUpScreen(activity: Activity, onSubmit: (user: User) -> Unit) {
    var fullnameState by remember { mutableStateOf("1") }
    var emailState by remember { mutableStateOf("2@.c") }
    var nationalIdState by remember { mutableStateOf("3") }
    var phoneNumberState by remember { mutableStateOf("4") }
    var passwordState by remember { mutableStateOf("duartesStrongPassword") }
    var errorMessage by remember { mutableStateOf("") }
    var acceptedTerms by remember { mutableStateOf(true) }
    var showDialog by remember { mutableStateOf(false) }

    var selectedDate by remember {
        val date = Clock.System.now().toLocalDateTime(TimeZone.UTC).date.minus(
            DatePeriod(18)
        )
        mutableStateOf(date)
    }
    val label = buildString {
        val dayOfMonth = selectedDate.dayOfMonth.toString().padStart(2, '0')
        val month = selectedDate.monthNumber.toString().padStart(2, '0')
        val year = (selectedDate.year).toString().padStart(4, '0')
        append("$dayOfMonth/$month/$year")
    }

    val focusManager = LocalFocusManager.current

    val windowSizeClass = calculateWindowSizeClass(activity).widthSizeClass
    val centered = windowSizeClass == WindowWidthSizeClass.Expanded

    val focus = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val onSubmit: () -> Unit = {
        if (selectedDate > Clock.System.now().toLocalDateTime(TimeZone.UTC).date.minus(
                DatePeriod(18)
            )
        ) {
            Toast.makeText(activity, "Age should be more than 18", Toast.LENGTH_SHORT).show()
        } else {
            onSubmit(
                User(
                    fullName = fullnameState,
                    email = emailState,
                    nationalId = nationalIdState.toLong(),
                    phoneNumber = phoneNumberState.toLong(),
                    dateOfBirth = 11111111,
                    password = passwordState
                )
            )
        }
    }

    @Composable
    fun TermsAndConditions(onClick: () -> Unit) {
        val fullText = "I accept the Terms & Conditions"
        val clickableText = "Terms & Conditions"
        val tag = "terms-and-conditions"

        val annotatedString = buildAnnotatedString {
            append(fullText)
            val start = fullText.indexOf(clickableText)
            val end = start + clickableText.length

            addStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                ),
                start = start,
                end = end
            )

            addStringAnnotation(
                tag = tag,
                annotation = "https://www.composables.com",
                start = start,
                end = end
            )
        }
        val uriHandler = LocalUriHandler.current
        ClickableText(
            style = MaterialTheme.typography.bodyMedium.copy(
                color = LocalContentColor.current
            ),
            text = annotatedString,
            onClick = { offset ->
                val string = annotatedString.getStringAnnotations(tag, offset, offset).firstOrNull()
                if (string != null) {
                    uriHandler.openUri("https://www.linkedin.com/company/deloitte/life/7738a52a-9b9e-4b1b-81ef-2b28934752cc/")
                } else {
                    onClick()
                }
            }
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState(0)),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 600.dp)
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 32.dp),
                horizontalAlignment = if (centered) Alignment.CenterHorizontally else Alignment.Start
            ) {
                Text(
                    text = "Create Free Account",
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(Modifier.height(24.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = fullnameState,
                    label = { Text("Full name") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focus.moveFocus(FocusDirection.Next)
                        }
                    ),
                    onValueChange = { fullnameState = it },
                    singleLine = true
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = emailState,
                    label = { Text("E-mail") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focus.moveFocus(FocusDirection.Next)
                        }
                    ),
                    onValueChange = { emailState = it },
                    singleLine = true
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = nationalIdState,
                    label = { Text("National ID") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focus.moveFocus(FocusDirection.Next)
                        }
                    ),
                    onValueChange = { nationalIdState = it },
                    singleLine = true
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = phoneNumberState,
                    label = { Text("Phone Number") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focus.moveFocus(FocusDirection.Next)
                        }
                    ),
                    onValueChange = { phoneNumberState = it },
                    singleLine = true
                )
                Spacer(Modifier.height(8.dp))
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    OutlinedTextField(
                        value = label,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier
                            .onFocusChanged {
                                showDialog = it.isFocused
                            }
                            .fillMaxWidth(),
                        label = { Text("Date of birth") },
                        trailingIcon = {
                            Icon(Icons.Filled.DateRange, contentDescription = "Select date")
                        }
                    )
                    if (showDialog) {
                        DateDialog(selectedDate, focusManager, {
                            showDialog = false
                        }, {
                            selectedDate = it
                        })
                    }
                }
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Password") },
                    isError = errorMessage.isNotBlank(),
                    supportingText = {
                        Text(errorMessage)
                    },
                    value = passwordState,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focus.clearFocus()
                            keyboardController?.hide()
                            onSubmit()
                        }
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = {
                        passwordState = it
                    },
                    singleLine = true
                )

                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.small)
                        .clickable { acceptedTerms = acceptedTerms.not() }
                        .minimumInteractiveComponentSize()
                        .padding(horizontal = 2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Checkbox(
                        checked = acceptedTerms,
                        onCheckedChange = null,
                    )
                    TermsAndConditions(onClick = { acceptedTerms = acceptedTerms.not() })
                }

                Spacer(Modifier.height(16.dp))
                Column(Modifier.padding(horizontal = 16.dp)) {
                    Button(
                        enabled = acceptedTerms && fullnameState.isNotBlank()
                                && emailState.isNotBlank()
                                && passwordState.isNotBlank()
                                && nationalIdState.isNotBlank()
                                && phoneNumberState.isNotBlank()
                                && emailState.contains("@") && emailState.contains("."),
                        onClick = onSubmit,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Sign Up")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateDialog(
    selectedDate: LocalDate,
    focusManager: FocusManager,
    dismissDialog: () -> Unit,
    selectDate: (LocalDate) -> Unit
) {
    val pickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDate
            .atStartOfDayIn(TimeZone.UTC)
            .toEpochMilliseconds()
    )
    DatePickerDialog(
        onDismissRequest = {
            dismissDialog()
            focusManager.clearFocus()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val instant = Instant.fromEpochMilliseconds(pickerState.selectedDateMillis!!)
                    selectDate(instant.toLocalDateTime(TimeZone.UTC).date)
                    instant.toEpochMilliseconds()
                    dismissDialog()
                    focusManager.clearFocus()
                }
            ) {
                Text("Select")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                dismissDialog()
                focusManager.clearFocus()
            }) {
                Text("Cancel")
            }
        },
    ) {//Clock.System.now().toLocalDateTime(TimeZone.UTC).date.minus(
//        DatePeriod(18)
//        )
        DatePicker(state = pickerState)
    }
}