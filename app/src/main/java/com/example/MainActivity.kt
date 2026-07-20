package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.json.JSONArray
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaType
import com.example.ui.theme.MyApplicationTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.CustomCredential
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                BmiCalculatorApp()
            }
        }
    }
}

val LocalIsDarkMode = staticCompositionLocalOf { false }

// Bento Theme Palette (Dynamic based on Dark Mode)
val BentoBg: Color
    @Composable
    get() = if (LocalIsDarkMode.current) Color(0xFF121212) else Color(0xFFFDF7FF)

val BentoDark: Color
    @Composable
    get() = if (LocalIsDarkMode.current) Color(0xFFF3EDF7) else Color(0xFF1D1B20)

val BentoPurple: Color
    @Composable
    get() = if (LocalIsDarkMode.current) Color(0xFFD0BCFF) else Color(0xFF6750A4)

val BentoLightPurple: Color
    @Composable
    get() = if (LocalIsDarkMode.current) Color(0xFF4F378B) else Color(0xFFEADDFF)

val BentoDeepPurple: Color
    @Composable
    get() = if (LocalIsDarkMode.current) Color(0xFFEADDFF) else Color(0xFF21005D)

val BentoGrayBg: Color
    @Composable
    get() = if (LocalIsDarkMode.current) Color(0xFF232128) else Color(0xFFF3EDF7)

val BentoLilac1: Color
    @Composable
    get() = if (LocalIsDarkMode.current) Color(0xFF382F4C) else Color(0xFFE8DEF8)

val BentoLilac2: Color
    @Composable
    get() = if (LocalIsDarkMode.current) Color(0xFF4F378B) else Color(0xFFD0BCFF)

val BentoMutedText: Color
    @Composable
    get() = if (LocalIsDarkMode.current) Color(0xFFCAB4D0) else Color(0xFF49454F)

val BentoBorder: Color
    @Composable
    get() = if (LocalIsDarkMode.current) Color(0xFF3C3843) else Color(0xFFCAC4D0)

val BentoCardBg: Color
    @Composable
    get() = if (LocalIsDarkMode.current) Color(0xFF1D1B20) else Color.White

// Data models & units
enum class UnitSystem {
    METRIC, IMPERIAL
}

enum class Gender {
    MALE, FEMALE
}

data class BmiRecord(
    val id: String = java.util.UUID.randomUUID().toString(),
    val bmi: Float,
    val categoryKey: String, // "underweight", "normal", "overweight", "obese"
    val heightStr: String,
    val weightStr: String,
    val time: String
)

data class Translation(
    val title: String,
    val languageLabel: String,
    val genderLabel: String,
    val male: String,
    val female: String,
    val height: String,
    val weight: String,
    val age: String,
    val calculate: String,
    val resultTitle: String,
    val underweight: String,
    val normal: String,
    val overweight: String,
    val obese: String,
    val metric: String,
    val imperial: String,
    val bmiPhrase: String,
    val tipUnderweight: String,
    val tipNormal: String,
    val tipOverweight: String,
    val tipObese: String,
    val historyTitle: String,
    val recentCalc: String,
    val clearHistory: String,
    val healthyRange: String,
    // Trends & Predictions
    val trendTitle: String,
    val trendSubtitle: String,
    val predictionTitle: String,
    val predictionSubtitle: String,
    val localModel: String,
    val localSlopeText: String,
    val localPredictText: String,
    val localFitText: String,
    val aiTrainBtn: String,
    val aiTraining: String,
    val predictionResult: String,
    val predictionBmi: String,
    val predictionTrajectory: String,
    val confidence: String,
    val recsTitle: String,
    val obstaclesTitle: String,
    val trendPlaceholder: String,
    val noModelResult: String
)

// Language list with their displays
data class LanguageOption(val code: String, val name: String, val flag: String)

val languagesList = listOf(
    LanguageOption("en", "English", "🇺🇸"),
    LanguageOption("fr", "Français", "🇫🇷"),
    LanguageOption("ha", "Hausa", "🇳🇬"),
    LanguageOption("ig", "Igbo", "🇳🇬"),
    LanguageOption("yo", "Yoruba", "🇳🇬")
)

fun getProfileTranslation(langCode: String, key: String): String {
    return when (langCode) {
        "fr" -> when (key) {
            "profile_title" -> "Profil de l'Utilisateur"
            "user_name" -> "Nom de l'utilisateur"
            "edit_name" -> "Modifier le nom"
            "save" -> "Enregistrer"
            "sign_in_google" -> "Se connecter avec Google"
            "sign_out" -> "Se déconnecter"
            "welcome_back" -> "Bon retour"
            "guest" -> "Invité"
            "enter_name_hint" -> "Entrez votre nom"
            "login_success" -> "Connexion réussie avec Google!"
            "switch_account" -> "Changer de compte"
            else -> key
        }
        "ha" -> when (key) {
            "profile_title" -> "Bayanin Shafi"
            "user_name" -> "Sunan mai amfani"
            "edit_name" -> "Gyara Suna"
            "save" -> "Ajiye"
            "sign_in_google" -> "Shiga da Google"
            "sign_out" -> "Fita"
            "welcome_back" -> "Sannu da dawowa"
            "guest" -> "Bako"
            "enter_name_hint" -> "Rubuta sunanka"
            "login_success" -> "An shiga cikin nasara ta Google!"
            "switch_account" -> "Canza Asusun"
            else -> key
        }
        "ig" -> when (key) {
            "profile_title" -> "Profaịlụ Onye Jiri"
            "user_name" -> "Aha onye ọrụ"
            "edit_name" -> "Dezie Aha"
            "save" -> "Chekwaa"
            "sign_in_google" -> "Banye na Google"
            "sign_out" -> "Pụọ"
            "welcome_back" -> "Nnabata ọma"
            "guest" -> "Onye Ọbịa"
            "enter_name_hint" -> "Tinye aha gị"
            "login_success" -> "Gị na Google banyere nke ọma!"
            "switch_account" -> "Gbanwee Akaụntụ"
            else -> key
        }
        "yo" -> when (key) {
            "profile_title" -> "Balu ti Olumulo"
            "user_name" -> "Orukọ rẹ"
            "edit_name" -> "Ṣatunkọ Orukọ"
            "save" -> "Fipamọ"
            "sign_in_google" -> "Wọle pẹlu Google"
            "sign_out" -> "Jade"
            "welcome_back" -> "Kaabo pada"
            "guest" -> "Alejo"
            "enter_name_hint" -> "Te oruko re sihin"
            "login_success" -> "Wọle ni aṣeyọri pẹlu Google!"
            "switch_account" -> "Yi Akaụntụ pada"
            else -> key
        }
        else -> when (key) { // English defaults
            "profile_title" -> "User Profile"
            "user_name" -> "User Name"
            "edit_name" -> "Edit Name"
            "save" -> "Save Preferences"
            "sign_in_google" -> "Sign in with Google"
            "sign_out" -> "Sign Out"
            "welcome_back" -> "Welcome back"
            "guest" -> "Guest User"
            "enter_name_hint" -> "Enter your name"
            "login_success" -> "Successfully signed in with Google!"
            "switch_account" -> "Switch Account"
            else -> key
        }
    }
}

val translations = mapOf(
    "en" to Translation(
        title = "BMI Calculator",
        languageLabel = "Language",
        genderLabel = "Gender",
        male = "Male",
        female = "Female",
        height = "Height",
        weight = "Weight",
        age = "Age",
        calculate = "Calculate BMI",
        resultTitle = "Your Result",
        underweight = "Underweight",
        normal = "Normal Weight",
        overweight = "Overweight",
        obese = "Obese",
        metric = "Metric (cm/kg)",
        imperial = "Imperial (in/lbs)",
        bmiPhrase = "Your BMI is",
        tipUnderweight = "Consider consulting a healthcare provider. Eating nutrient-dense foods can help build a healthy weight.",
        tipNormal = "Great job! Maintain your healthy weight with a balanced diet and regular physical activity.",
        tipOverweight = "Focus on active living and a balanced diet. Small daily changes can lead to great health benefits.",
        tipObese = "Consulting a healthcare provider or a nutritionist is recommended to plan a healthy path forward.",
        historyTitle = "Calculation History",
        recentCalc = "Recent Calculations",
        clearHistory = "Clear",
        healthyRange = "Healthy BMI range is 18.5 - 24.9",
        trendTitle = "BMI Trend Tracker",
        trendSubtitle = "Your weight progression over time",
        predictionTitle = "Predictive Modeling Engine",
        predictionSubtitle = "AI & Machine Learning Insights",
        localModel = "Local Training Model",
        localSlopeText = "Trend Slope",
        localPredictText = "Next Projected BMI",
        localFitText = "Model Fit (R²)",
        aiTrainBtn = "Train AI Predictor",
        aiTraining = "Training Model...",
        predictionResult = "AI Model Forecast",
        predictionBmi = "Forecasted BMI (4 Weeks)",
        predictionTrajectory = "Expected Trajectory",
        confidence = "Model Confidence",
        recsTitle = "AI Recommended Actions",
        obstaclesTitle = "Avoid These Obstacles",
        trendPlaceholder = "Add 2 or more calculations to build your trend chart and train your predictive models!",
        noModelResult = "No active AI forecast. Click the button above to train the model."
    ),
    "fr" to Translation(
        title = "Calculateur d'IMC",
        languageLabel = "Langue",
        genderLabel = "Sexe",
        male = "Homme",
        female = "Femme",
        height = "Taille",
        weight = "Poids",
        age = "Âge",
        calculate = "Calculer l'IMC",
        resultTitle = "Votre Résultat",
        underweight = "Poids insuffisant",
        normal = "Poids normal",
        overweight = "Surpoids",
        obese = "Obésité",
        metric = "Métrique (cm/kg)",
        imperial = "Impérial (in/lbs)",
        bmiPhrase = "Votre IMC est de",
        tipUnderweight = "Pensez à consulter un médecin. Manger des aliments riches en nutriments peut vous aider à atteindre un poids sain.",
        tipNormal = "Excellent travail! Maintenez votre poids santé avec une alimentation équilibrée et une activité physique régulière.",
        tipOverweight = "Concentrez-vous sur un mode de vie actif et une alimentation équilibrée. De petits changements quotidiens peuvent apporter de grands bienfaits.",
        tipObese = "Il est recommandé de consulter un médecin ou un nutritionniste pour planifier un parcours de santé adapté.",
        historyTitle = "Historique",
        recentCalc = "Calculs récents",
        clearHistory = "Effacer",
        healthyRange = "L'intervalle d'IMC sain est de 18,5 à 24,9",
        trendTitle = "Suivi des Tendances IMC",
        trendSubtitle = "Progression de votre poids",
        predictionTitle = "Moteur de Modélisation Prédictive",
        predictionSubtitle = "Analyses de l'IA et Machine Learning",
        localModel = "Modèle d'Entraînement Local",
        localSlopeText = "Pente de la Tendance",
        localPredictText = "Prochain IMC Projeté",
        localFitText = "Ajustement du Modèle (R²)",
        aiTrainBtn = "Entraîner le Prédicteur IA",
        aiTraining = "Entraînement du Modèle...",
        predictionResult = "Prévisions de l'IA",
        predictionBmi = "IMC Prévu (4 Semaines)",
        predictionTrajectory = "Trajectoire Attendue",
        confidence = "Confiance du Modèle",
        recsTitle = "Actions Recommandées par l'IA",
        obstaclesTitle = "Obstacles à Éviter",
        trendPlaceholder = "Ajoutez au moins 2 calculs pour tracer votre graphique et entraîner les modèles prédictifs !",
        noModelResult = "Aucune prévision IA active. Cliquez ci-dessus pour entraîner le modèle."
    ),
    "ha" to Translation(
        title = "Ma'aunin BMI",
        languageLabel = "Harshe",
        genderLabel = "Jinsi",
        male = "Namiji",
        female = "Mace",
        height = "Tsayi",
        weight = "Nauyi",
        age = "Shekaru",
        calculate = "Auna BMI",
        resultTitle = "Sakamakonka",
        underweight = "Karancin Nauyi",
        normal = "Nauyi Mai Kyau",
        overweight = "Kiba Matsakaici",
        obese = "Yawan Kiba",
        metric = "Metrik (cm/kg)",
        imperial = "Inperiyal (in/lbs)",
        bmiPhrase = "Ma'aunin BMI dinka shine",
        tipUnderweight = "Yi la'akari da tuntuɓar likita. Cin abinci mai gina jiki zai iya taimaka maka samun ingantaccen nauyi.",
        tipNormal = "Madalla! Ci gaba da kula da lafiyar jikinka ta hynar cin abinci mai kyau da motsa jiki akai-akai.",
        tipOverweight = "Maida hankali kan motsa jiki da cin abinci mai kyau. Ƙananan canje-canje na yau da kullum na iya kawo babban amfani.",
        tipObese = "Ana ba da shawarar tuntuɓar ƙwararren likita ko masanin abinci don tsara hanyar samun lafiya.",
        historyTitle = "Tarihin Auna",
        recentCalc = "Aunawa na Kusa",
        clearHistory = "Goge",
        healthyRange = "Ma'auni mai kyau shine tsakanin 18.5 - 24.9",
        trendTitle = "Tarihin Yanayin BMI",
        trendSubtitle = "Sauye-sauyen nauyinka a tsawon lokaci",
        predictionTitle = "Injin Hasashen Lafiya",
        predictionSubtitle = "Ilimin Na'ura na AI",
        localModel = "Samfurin Koyarwa na Cikin Gida",
        localSlopeText = "Guduwar Layi",
        localPredictText = "Hasashen BMI na Gaba",
        localFitText = "Daidaiton Samfuri (R²)",
        aiTrainBtn = "Koyar da Mai Hasashen AI",
        aiTraining = "Ana Koyar da Samfuri...",
        predictionResult = "Sakamakon Hasashen AI",
        predictionBmi = "Hasashen BMI (Mako 4)",
        predictionTrajectory = "Hanyar Sauyi da Ake Tsammani",
        confidence = "Karfin Hasashe",
        recsTitle = "Hanyoyin da AI Ke Shawartawa",
        obstaclesTitle = "Abubuwan da Ya Kamata A Guje wa",
        trendPlaceholder = "Ƙara auna nauyi sau 2 ko fiye don gina jadawali da koyar da samfurin hasashe!",
        noModelResult = "Babu hasashen AI mai aiki. Latsa maballin da ke sama don fara koyarwa."
    ),
    "ig" to Translation(
        title = "Ihe Nleba BMI",
        languageLabel = "Asụsụ",
        genderLabel = "Okike",
        male = "Nwoke",
        female = "Nwanyị",
        height = "Ogo",
        weight = "Arọ",
        age = "Arọ Ndụ",
        calculate = "Gbakọọ BMI",
        resultTitle = "Ihe Npụta Gị",
        underweight = "Arọ Dị Ala",
        normal = "Arọ Dị Mma",
        overweight = "Ibu Oke",
        obese = "Oke Ibu Nwere Ahụ Ọkụ",
        metric = "Metrik (cm/kg)",
        imperial = "Imperial (in/lbs)",
        bmiPhrase = "BMI gị bụ",
        tipUnderweight = "Tụlee ịhụ dọkịta. Iri nri na-edozi ahụ nwere ike inyere gị aka inweta arọ dị mma.",
        tipNormal = "I meela nke ọma! Nọgide na-enwe arọ dị mma site n'iri nri dị mma na imega ahụ mgbe niile.",
        tipOverweight = "Lekwasị anya na mmega ahụ na nri dị mma. Obere mgbanwe kwa ụbọchị nwere ike iweta nnukwu uru ahụike.",
        tipObese = "A na-atụ aro ka gị na dọkịta ma ọ bụ onye na-ahụ maka nri kwurịta okwu iji rụpụta atụmatụ ahụike.",
        historyTitle = "Akụkọ Ihe Ndị Emere",
        recentCalc = "Ihe Ndị Emetụbeghị Anya",
        clearHistory = "Hichaa",
        healthyRange = "Arọ BMI dị mma bụ 18.5 - 24.9",
        trendTitle = "Ebe Nleba Anya Trend BMI",
        trendSubtitle = "Ọganihu arọ gị ka oge na-aga",
        predictionTitle = "Injin Nleba Anya na Ọdịnihu",
        predictionSubtitle = "AI na Machine Learning echiche",
        localModel = "Ụdị Ọzụzụ Mpaghara",
        localSlopeText = "Ngosipụta Slope",
        localPredictText = "BMI Ọzọ E Mere Atụmatụ",
        localFitText = "Dị mma nke ụdị (R²)",
        aiTrainBtn = "Zụọ Onye Amụma AI",
        aiTraining = "Ọzụzụ Ụdị...",
        predictionResult = "Amụma AI Ihe Npụta",
        predictionBmi = "BMI Amụma (Izu 4)",
        predictionTrajectory = "Atụmatụ Trajectory",
        confidence = "Ntụkwasị Obi Ụdị",
        recsTitle = "Omume AI tụrụ aro",
        obstaclesTitle = "Zere Ihe Ndị a na-egbochi",
        trendPlaceholder = "Tinye ngụkọ 2 ma ọ bụ karịa iji rụpụta chaatị gị na ịzụ ụdị amụma gị!",
        noModelResult = "Enweghị amụma AI na-arụ ọrụ. Pịa bọtịnụ dị n'elu ka ịzụọ ụdị ahụ."
    ),
    "yo" to Translation(
        title = "Olùṣírò BMI",
        languageLabel = "Èdè",
        genderLabel = "Giri",
        male = "Ọkùnrin",
        female = "Obìnrin",
        height = "Gíga",
        weight = "Ìwọ̀n",
        age = "Ọjọ́ Orí",
        calculate = "Ṣírò BMI rẹ",
        resultTitle = "Àbájáde Rẹ",
        underweight = "Ìwọ̀n Kéré Ju",
        normal = "Ìwọ̀n Tí Ó Tọ́",
        overweight = "Ìwọ̀n Tó Ju Bẹ́ẹ̀ Lọ",
        obese = "Sànra Jù",
        metric = "Mẹ́tíríìkì (cm/kg)",
        imperial = "Ìpínlẹ̀ Imperial (in/lbs)",
        bmiPhrase = "BMI rẹ jẹ́",
        tipUnderweight = "Gba ìmọ̀ràn lọ́dọ̀ dọkịta. Jíjẹ oúnjẹ aṣaralóore le ràn ọ́ lọ́wọ́ láti ní ìwọ̀n tí ó tọ́.",
        tipNormal = "Káàre! Tẹ̀síwájú láti máa tọ́jú ìwọ̀n rẹ pẹ̀lú oúnjẹ tó dára àti eré ìdárayá déédéé.",
        tipOverweight = "Gbájú mọ́ eré ìdárayá àti oúnjẹ tó tọ́. Àwọn ìyípadà kékeré lojojúmọ́ le mú àǹfààní ńlá wá.",
        tipObese = "A gbani nímọ̀ràn láti rí dọkịta tàbí onímọ̀ nípa oúnjẹ láti gba ọ̀nà tó tọ́ fún ìlera.",
        historyTitle = "Ìtàn Ìṣírò",
        recentCalc = "Àwọn Ìṣírò Àìpẹ́",
        clearHistory = "Pa rẹ́",
        healthyRange = "Iwọn BMI to dara jẹ 18.5 - 24.9",
        trendTitle = "Ìtàn Àpẹẹrẹ BMI Rẹ",
        trendSubtitle = "Bí ìwọ̀n rẹ ṣe ń yí padà lórí àkókò",
        predictionTitle = "Ẹrọ Ìṣirò Àbájáde Ọjọ́ Iwájú",
        predictionSubtitle = "Ìmọ̀ AI àti Kíkọ́ Ẹ̀rọ",
        localModel = "Àpẹẹrẹ Kíkọ́ Ẹ̀rọ ti Agbègbè",
        localSlopeText = "Gíga àti Rírọ̀ Laini",
        localPredictText = "BMI Àbájáde Tó Kàn",
        localFitText = "Àbájáde Àpẹẹrẹ (R²)",
        aiTrainBtn = "Kọ́ Olùsọtẹ́lẹ̀ AI Rẹ",
        aiTraining = "Kíkọ́ Àpẹẹrẹ lọ́wọ́...",
        predictionResult = "Àbájáde Ìsọtẹ́lẹ̀ AI",
        predictionBmi = "BMI Tó Sọtẹ́lẹ̀ (Ọ̀sẹ̀ 4)",
        predictionTrajectory = "Ìṣerere Lórí Ìwọ̀n",
        confidence = "Ìgbẹ́kẹ̀lé Àbájáde AI",
        recsTitle = "Àwọn Ìgbésẹ̀ tí AI Dábàá",
        obstaclesTitle = "Yẹra fún Àwọn Ìdènà Wọ̀nyí",
        trendPlaceholder = "Ṣe ìṣìrò ní ìgbà méjì tàbí jù bẹ́ẹ̀ lọ láti kọ́ àtẹ rẹ àti láti kọ́ àpẹẹrẹ ìsọtẹ́lẹ̀!",
        noModelResult = "Kò sí ìsọtẹ́lẹ̀ AI tó n fẹ́ báyìí. Tẹ bọtini lókè láti kọ́ àpẹẹrẹ."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BmiCalculatorApp() {
    // Application States
    var selectedLangCode by remember { mutableStateOf("en") }
    val trans = translations[selectedLangCode] ?: translations["en"]!!

    val context = LocalContext.current
    val sharedPreferences = remember { context.getSharedPreferences("bmi_prefs", Context.MODE_PRIVATE) }
    var isDarkMode by remember { mutableStateOf(sharedPreferences.getBoolean("is_dark_mode", false)) }
    var userName by remember { mutableStateOf(sharedPreferences.getString("user_name", "") ?: "") }
    var userEmail by remember { mutableStateOf(sharedPreferences.getString("user_email", "") ?: "") }
    var userPhotoUrl by remember { mutableStateOf(sharedPreferences.getString("user_photo_url", "") ?: "") }
    var isLoggedIn by remember { mutableStateOf(sharedPreferences.getBoolean("is_logged_in", false)) }

    var showProfileDialog by remember { mutableStateOf(false) }

    var unitSystem by remember { mutableStateOf(UnitSystem.METRIC) }
    var gender by remember { mutableStateOf(Gender.MALE) }

    // Steppers / Input states
    var heightCm by remember { mutableStateOf(170f) }
    var heightFeet by remember { mutableStateOf(5) }
    var heightInches by remember { mutableStateOf(7) }

    var weightKg by remember { mutableStateOf(70f) }
    var weightLbs by remember { mutableStateOf(154f) }

    var age by remember { mutableStateOf(25) }

    var bmiResult by remember { mutableStateOf<Float?>(null) }
    var resultCategoryKey by remember { mutableStateOf("") } // underweight, normal, overweight, obese

    val history = remember { mutableStateListOf<BmiRecord>() }
    var isLangMenuExpanded by remember { mutableStateOf(false) }
    var activeTab by remember { mutableStateOf("HOME") }

    // Perform BMI calculation
    fun calculateBmi() {
        val calculatedBmi = if (unitSystem == UnitSystem.METRIC) {
            val heightM = heightCm / 100f
            if (heightM > 0) weightKg / (heightM * heightM) else 0f
        } else {
            val totalInches = (heightFeet * 12) + heightInches
            if (totalInches > 0) {
                703f * weightLbs / (totalInches * totalInches)
            } else 0f
        }

        bmiResult = calculatedBmi

        resultCategoryKey = when {
            calculatedBmi < 18.5f -> "underweight"
            calculatedBmi in 18.5f..24.9f -> "normal"
            calculatedBmi in 25.0f..29.9f -> "overweight"
            else -> "obese"
        }

        // Add to history
        val heightStr = if (unitSystem == UnitSystem.METRIC) {
            "${heightCm.roundToInt()} cm"
        } else {
            "$heightFeet' $heightInches\""
        }

        val weightStr = if (unitSystem == UnitSystem.METRIC) {
            "${weightKg.roundToInt()} kg"
        } else {
            "${weightLbs.roundToInt()} lbs"
        }

        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

        history.add(
            0, // Add to top
            BmiRecord(
                bmi = calculatedBmi,
                categoryKey = resultCategoryKey,
                heightStr = heightStr,
                weightStr = weightStr,
                time = currentTime
            )
        )
    }

    val snackbarHostState = remember { SnackbarHostState() }

    CompositionLocalProvider(LocalIsDarkMode provides isDarkMode) {
        MyApplicationTheme(darkTheme = isDarkMode) {
            Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "HEALTH TRACKER",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = BentoPurple,
                            letterSpacing = 1.5.sp
                        )
                        Text(
                            text = trans.title,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = BentoDark
                        )
                    }
                },
                actions = {
                    Box(modifier = Modifier.padding(end = 8.dp)) {
                        IconButton(
                            onClick = { isLangMenuExpanded = true },
                            modifier = Modifier.testTag("lang_menu_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = trans.languageLabel,
                                tint = BentoPurple
                            )
                        }
                        DropdownMenu(
                            expanded = isLangMenuExpanded,
                            onDismissRequest = { isLangMenuExpanded = false }
                        ) {
                            languagesList.forEach { lang ->
                                DropdownMenuItem(
                                    text = { Text("${lang.flag} ${lang.name}") },
                                    onClick = {
                                        selectedLangCode = lang.code
                                        isLangMenuExpanded = false
                                    }
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(BentoLightPurple, CircleShape)
                            .clickable { showProfileDialog = true }
                            .testTag("top_profile_avatar"),
                        contentAlignment = Alignment.Center
                    ) {
                        if (userName.isNotBlank()) {
                            Text(
                                text = userName.trim().take(1).uppercase(),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = BentoDeepPurple
                            )
                        } else {
                            Text("👤", fontSize = 20.sp)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BentoBg,
                    titleContentColor = BentoDark
                )
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                color = BentoGrayBg,
                border = androidx.compose.foundation.BorderStroke(0.5.dp, BentoBorder)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // HOME Tab
                    val isHome = activeTab == "HOME"
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .clickable { activeTab = "HOME" }
                            .alpha(if (isHome) 1.0f else 0.5f)
                            .testTag("tab_home")
                    ) {
                        Box(
                            modifier = Modifier
                                .background(if (isHome) BentoLightPurple else Color.Transparent, RoundedCornerShape(16.dp))
                                .padding(horizontal = 20.dp, vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("🏠", fontSize = 18.sp)
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "HOME",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = BentoDark
                        )
                    }

                    // TRENDS Tab
                    val isTrends = activeTab == "TRENDS"
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .clickable { activeTab = "TRENDS" }
                            .alpha(if (isTrends) 1.0f else 0.5f)
                            .testTag("tab_trends")
                    ) {
                        Box(
                            modifier = Modifier
                                .background(if (isTrends) BentoLightPurple else Color.Transparent, RoundedCornerShape(16.dp))
                                .padding(horizontal = 20.dp, vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("📊", fontSize = 18.sp)
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "TRENDS",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = BentoDark
                        )
                    }

                    // DIET Tab
                    val isDiet = activeTab == "DIET"
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .clickable { activeTab = "DIET" }
                            .alpha(if (isDiet) 1.0f else 0.5f)
                            .testTag("tab_diet")
                    ) {
                        Box(
                            modifier = Modifier
                                .background(if (isDiet) BentoLightPurple else Color.Transparent, RoundedCornerShape(16.dp))
                                .padding(horizontal = 20.dp, vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("🥗", fontSize = 18.sp)
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "DIET",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = BentoDark
                        )
                    }

                    // SETTINGS Tab
                    val isSettings = activeTab == "SETTINGS"
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .clickable { activeTab = "SETTINGS" }
                            .alpha(if (isSettings) 1.0f else 0.5f)
                            .testTag("tab_settings")
                    ) {
                        Box(
                            modifier = Modifier
                                .background(if (isSettings) BentoLightPurple else Color.Transparent, RoundedCornerShape(16.dp))
                                .padding(horizontal = 20.dp, vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("⚙️", fontSize = 18.sp)
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "SETTINGS",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = BentoDark
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        when (activeTab) {
            "HOME" -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(BentoBg),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 40.dp
                    )
                ) {
                    // 1. Beautiful Hero Illustration Graphic Bento Card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    shape = RoundedCornerShape(28.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = R.drawable.img_gym_exercise_1784415121575),
                            contentDescription = "Abstract Gym Exercise Illustration",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.5f)
                                        )
                                    )
                                )
                        )
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(20.dp)
                        ) {
                            val greetingPrefix = getProfileTranslation(selectedLangCode, "welcome_back")
                            Text(
                                text = if (userName.isNotBlank()) "$greetingPrefix, $userName!" else trans.title,
                                color = Color.White,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif
                            )
                            Text(
                                text = "Track • Analyze • Improve".uppercase(),
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 10.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = 1.5.sp
                            )
                        }
                    }
                }
            }

            // 2. Inline Interactive Language Selector (Bento Card)
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(containerColor = BentoGrayBg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = trans.languageLabel + " / Yanayin Harshe",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.styleSchemeTitle,
                            color = BentoMutedText,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(languagesList) { lang ->
                                val isSelected = selectedLangCode == lang.code
                                val containerColor by animateColorAsState(
                                    targetValue = if (isSelected) BentoPurple else BentoCardBg,
                                    label = "langBoxColor"
                                )
                                val textColor = if (isSelected) Color.White else BentoMutedText

                                Surface(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(20.dp))
                                        .clickable { selectedLangCode = lang.code }
                                        .testTag("lang_chip_${lang.code}"),
                                    color = containerColor,
                                    border = if (!isSelected) androidx.compose.foundation.BorderStroke(1.dp, BentoBorder) else null,
                                    shape = RoundedCornerShape(20.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(text = lang.flag, modifier = Modifier.padding(end = 6.dp))
                                        Text(
                                            text = lang.name,
                                            color = textColor,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 14.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 3. Metric / Imperial Unit Tab Row (Bento Card)
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = BentoGrayBg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        UnitSystem.values().forEach { system ->
                            val isSelected = unitSystem == system
                            val chipBg by animateColorAsState(
                                targetValue = if (isSelected) Color.White else Color.Transparent,
                                label = "unitBgColor"
                            )
                            val contentColor = if (isSelected) BentoPurple else BentoMutedText

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(18.dp))
                                    .background(chipBg)
                                    .clickable { unitSystem = system }
                                    .padding(vertical = 12.dp)
                                    .testTag("unit_tab_${system.name.lowercase()}"),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (system == UnitSystem.METRIC) trans.metric else trans.imperial,
                                    color = contentColor,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }

            // 4. Bento Row: Gender Selector & Age Inputs
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Left: Gender Selector Cell
                    Card(
                        modifier = Modifier
                            .weight(1.2f)
                            .height(150.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = BentoGrayBg),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = trans.genderLabel.uppercase(),
                                fontSize = 11.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = BentoMutedText,
                                letterSpacing = 1.5.sp
                            )
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                // Male
                                val maleSelected = gender == Gender.MALE
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(if (maleSelected) BentoPurple else BentoCardBg)
                                        .clickable { gender = Gender.MALE }
                                        .padding(vertical = 12.dp)
                                        .testTag("gender_male"),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Male,
                                            contentDescription = trans.male,
                                            tint = if (maleSelected) Color.White else BentoMutedText,
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Text(
                                            text = trans.male,
                                            color = if (maleSelected) Color.White else BentoMutedText,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                                
                                // Female
                                val femaleSelected = gender == Gender.FEMALE
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(if (femaleSelected) BentoPurple else BentoCardBg)
                                        .clickable { gender = Gender.FEMALE }
                                        .padding(vertical = 12.dp)
                                        .testTag("gender_female"),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Female,
                                            contentDescription = trans.female,
                                            tint = if (femaleSelected) Color.White else BentoMutedText,
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Text(
                                            text = trans.female,
                                            color = if (femaleSelected) Color.White else BentoMutedText,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Right: Age Cell
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(150.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = BentoGrayBg),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = trans.age.uppercase(),
                                fontSize = 11.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = BentoMutedText,
                                letterSpacing = 1.5.sp
                            )
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "$age",
                                    fontSize = 28.sp,
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.Bold,
                                    color = BentoDark
                                )
                                
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    ButtonStepperBento(
                                        icon = Icons.Default.Remove,
                                        testTag = "age_minus",
                                        onClick = { if (age > 2) age -= 1 },
                                        accentColor = BentoMutedText,
                                        buttonSize = 26.dp,
                                        iconSize = 14.dp
                                    )
                                    ButtonStepperBento(
                                        icon = Icons.Default.Add,
                                        testTag = "age_plus",
                                        onClick = { if (age < 120) age += 1 },
                                        accentColor = BentoMutedText,
                                        buttonSize = 26.dp,
                                        iconSize = 14.dp
                                    )
                                }
                            }
                            
                            Spacer(modifier = Modifier.height(2.dp))
                            
                            Slider(
                                value = age.toFloat(),
                                onValueChange = { age = it.roundToInt() },
                                valueRange = 2f..120f,
                                modifier = Modifier.fillMaxWidth().height(24.dp).testTag("age_slider"),
                                colors = SliderDefaults.colors(
                                    thumbColor = BentoMutedText,
                                    activeTrackColor = BentoMutedText,
                                    inactiveTrackColor = BentoMutedText.copy(alpha = 0.2f)
                                )
                            )
                        }
                    }
                }
            }

            // 5. Height & Weight Bento Column Grid Card (Aspect Ratios)
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Height Bento Cell (Left)
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(200.dp),
                        shape = RoundedCornerShape(28.dp),
                        colors = CardDefaults.cardColors(containerColor = BentoLilac1),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = trans.height.uppercase(),
                                fontSize = 11.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = BentoPurple,
                                letterSpacing = 1.5.sp
                            )
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = if (unitSystem == UnitSystem.METRIC) {
                                            "${heightCm.roundToInt()}"
                                        } else {
                                            "$heightFeet'$heightInches\""
                                        },
                                        fontSize = 32.sp,
                                        fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight.Bold,
                                        color = BentoDark
                                    )
                                    Text(
                                        text = if (unitSystem == UnitSystem.METRIC) "cm" else "ft/in",
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Bold,
                                        color = BentoPurple.copy(alpha = 0.8f)
                                    )
                                }
                                
                                // Stepper controls in row
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    ButtonStepperBento(
                                        icon = Icons.Default.Remove,
                                        testTag = if (unitSystem == UnitSystem.METRIC) "height_minus" else "height_feet_minus",
                                        onClick = {
                                            if (unitSystem == UnitSystem.METRIC) {
                                                if (heightCm > 100f) heightCm -= 1f
                                            } else {
                                                val totalIn = (heightFeet * 12 + heightInches) - 1
                                                if (totalIn >= 36) {
                                                    heightFeet = totalIn / 12
                                                    heightInches = totalIn % 12
                                                }
                                            }
                                        },
                                        accentColor = BentoPurple,
                                        buttonSize = 28.dp,
                                        iconSize = 16.dp
                                    )
                                    ButtonStepperBento(
                                        icon = Icons.Default.Add,
                                        testTag = if (unitSystem == UnitSystem.METRIC) "height_plus" else "height_feet_plus",
                                        onClick = {
                                            if (unitSystem == UnitSystem.METRIC) {
                                                if (heightCm < 220f) heightCm += 1f
                                            } else {
                                                val totalIn = (heightFeet * 12 + heightInches) + 1
                                                if (totalIn <= 96) {
                                                    heightFeet = totalIn / 12
                                                    heightInches = totalIn % 12
                                                }
                                            }
                                        },
                                        accentColor = BentoPurple,
                                        buttonSize = 28.dp,
                                        iconSize = 16.dp
                                    )
                                }
                            }
                            
                            // Fully draggable Slider for Height
                            if (unitSystem == UnitSystem.METRIC) {
                                Slider(
                                    value = heightCm,
                                    onValueChange = { heightCm = it },
                                    valueRange = 100f..220f,
                                    modifier = Modifier.fillMaxWidth().height(24.dp).testTag("height_slider"),
                                    colors = SliderDefaults.colors(
                                        thumbColor = BentoPurple,
                                        activeTrackColor = BentoPurple,
                                        inactiveTrackColor = BentoPurple.copy(alpha = 0.2f)
                                    )
                                )
                            } else {
                                val currentTotalInches = (heightFeet * 12 + heightInches).toFloat()
                                Slider(
                                    value = currentTotalInches,
                                    onValueChange = { totalIn ->
                                        val totalInchesInt = totalIn.roundToInt()
                                        heightFeet = totalInchesInt / 12
                                        heightInches = totalInchesInt % 12
                                    },
                                    valueRange = 36f..96f,
                                    modifier = Modifier.fillMaxWidth().height(24.dp).testTag("height_slider"),
                                    colors = SliderDefaults.colors(
                                        thumbColor = BentoPurple,
                                        activeTrackColor = BentoPurple,
                                        inactiveTrackColor = BentoPurple.copy(alpha = 0.2f)
                                    )
                                )
                            }
                        }
                    }

                    // Weight Bento Cell (Right)
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(200.dp),
                        shape = RoundedCornerShape(28.dp),
                        colors = CardDefaults.cardColors(containerColor = BentoLilac2),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = trans.weight.uppercase(),
                                fontSize = 11.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = BentoDeepPurple,
                                letterSpacing = 1.5.sp
                            )
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = if (unitSystem == UnitSystem.METRIC) {
                                            "${weightKg.roundToInt()}"
                                        } else {
                                            "${weightLbs.roundToInt()}"
                                        },
                                        fontSize = 32.sp,
                                        fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight.Bold,
                                        color = BentoDeepPurple
                                    )
                                    Text(
                                        text = if (unitSystem == UnitSystem.METRIC) "kg" else "lbs",
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Bold,
                                        color = BentoDeepPurple.copy(alpha = 0.8f)
                                    )
                                }
                                
                                // Stepper controls in row
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    ButtonStepperBento(
                                        icon = Icons.Default.Remove,
                                        testTag = "weight_minus",
                                        onClick = {
                                            if (unitSystem == UnitSystem.METRIC) {
                                                if (weightKg > 30f) weightKg -= 1f
                                            } else {
                                                if (weightLbs > 60f) weightLbs -= 1f
                                            }
                                        },
                                        accentColor = BentoDeepPurple,
                                        buttonSize = 28.dp,
                                        iconSize = 16.dp
                                    )
                                    ButtonStepperBento(
                                        icon = Icons.Default.Add,
                                        testTag = "weight_plus",
                                        onClick = {
                                            if (unitSystem == UnitSystem.METRIC) {
                                                if (weightKg < 180f) weightKg += 1f
                                            } else {
                                                if (weightLbs < 400f) weightLbs += 1f
                                            }
                                        },
                                        accentColor = BentoDeepPurple,
                                        buttonSize = 28.dp,
                                        iconSize = 16.dp
                                    )
                                }
                            }
                            
                            // Fully draggable Slider for Weight
                            Slider(
                                value = if (unitSystem == UnitSystem.METRIC) weightKg else weightLbs,
                                onValueChange = {
                                    if (unitSystem == UnitSystem.METRIC) {
                                        weightKg = it
                                    } else {
                                        weightLbs = it
                                    }
                                },
                                valueRange = if (unitSystem == UnitSystem.METRIC) 30f..180f else 60f..400f,
                                modifier = Modifier.fillMaxWidth().height(24.dp).testTag("weight_slider"),
                                colors = SliderDefaults.colors(
                                    thumbColor = BentoDeepPurple,
                                    activeTrackColor = BentoDeepPurple,
                                    inactiveTrackColor = BentoDeepPurple.copy(alpha = 0.2f)
                                )
                            )
                        }
                    }
                }
            }

            // 6. Calculate Button
            item {
                Button(
                    onClick = { calculateBmi() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .testTag("calculate_button"),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BentoPurple,
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                ) {
                    Text(
                        text = trans.calculate.uppercase(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp
                    )
                }
            }

            // 7. Results Panel (Animated Dark Bento Card)
            item {
                AnimatedVisibility(
                    visible = bmiResult != null,
                    enter = fadeIn() + slideInVertically(
                        initialOffsetY = { 50 },
                        animationSpec = spring(dampingRatio = 0.8f, stiffness = Spring.StiffnessLow)
                    ),
                    exit = fadeOut()
                ) {
                    bmiResult?.let { score ->
                        val (categoryText, categoryColor, categoryTips) = when (resultCategoryKey) {
                            "underweight" -> Triple(trans.underweight, Color(0xFF60A5FA), trans.tipUnderweight)
                            "normal" -> Triple(trans.normal, Color(0xFF34D399), trans.tipNormal)
                            "overweight" -> Triple(trans.overweight, Color(0xFFFBBF24), trans.tipOverweight)
                            else -> Triple(trans.obese, Color(0xFFF87171), trans.tipObese)
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("result_card"),
                            shape = RoundedCornerShape(28.dp),
                            colors = CardDefaults.cardColors(containerColor = BentoDark),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp)
                            ) {
                                // Glowing status indicator top left
                                Row(
                                    modifier = Modifier.align(Alignment.TopStart),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp)
                                            .background(categoryColor, CircleShape)
                                    )
                                    Text(
                                        text = categoryText.uppercase(),
                                        color = categoryColor,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        letterSpacing = 1.2.sp
                                    )
                                }

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 24.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    Text(
                                        text = trans.bmiPhrase,
                                        color = Color(0xFFCAC4D0),
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium
                                    )

                                    Text(
                                        text = String.format(Locale.US, "%.1f", score),
                                        fontSize = 72.sp,
                                        fontWeight = FontWeight.Light,
                                        color = Color.White,
                                        letterSpacing = (-1).sp,
                                        textAlign = TextAlign.Center
                                    )

                                    // Progress Bar representing index on spectrum
                                    val progressFractionFraction = remember(score) {
                                        val clamped = score.coerceIn(10f, 40f)
                                        (clamped - 10f) / 30f
                                    }
                                    LinearProgressIndicator(
                                        progress = { progressFractionFraction },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(4.dp)
                                            .clip(RoundedCornerShape(2.dp)),
                                        color = categoryColor,
                                        trackColor = Color.White.copy(alpha = 0.15f)
                                    )

                                    // Description tip
                                    Text(
                                        text = categoryTips,
                                        color = Color(0xFFE6E1E5),
                                        fontSize = 13.sp,
                                        lineHeight = 18.sp,
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Normal,
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    )

                                    // Healthy reference
                                    Text(
                                        text = trans.healthyRange,
                                        color = Color(0xFFCAC4D0),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }
                }
            }

                }
            }

            "TRENDS" -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(BentoBg),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 40.dp
                    )
                ) {
                    // 7.2. Trend Chart Bento Box
                    item {
                        BmiTrendChart(
                            history = history,
                            trans = trans
                        )
                    }

                    // 7.3. AI Predictive Modeling Engine
                    item {
                        AiPredictiveCard(
                            history = history,
                            age = age,
                            gender = gender,
                            unitSystem = unitSystem,
                            heightCm = heightCm,
                            heightFeet = heightFeet,
                            heightInches = heightInches,
                            weightKg = weightKg,
                            weightLbs = weightLbs,
                            trans = trans
                        )
                    }

                    // 8. Calculations History Bento Card
                    if (history.isNotEmpty()) {
                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(28.dp),
                                colors = CardDefaults.cardColors(containerColor = BentoGrayBg),
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(20.dp),
                                    verticalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.History,
                                                contentDescription = trans.historyTitle,
                                                tint = BentoPurple,
                                                modifier = Modifier.size(20.dp)
                                            )
                                            Text(
                                                text = trans.historyTitle.uppercase(),
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 12.sp,
                                                color = BentoMutedText,
                                                letterSpacing = 1.1.sp
                                            )
                                        }
                                        Text(
                                            text = trans.clearHistory.uppercase(),
                                            color = MaterialTheme.colorScheme.error,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp,
                                            letterSpacing = 1.1.sp,
                                            modifier = Modifier
                                                .clickable { history.clear() }
                                                .testTag("clear_history_button")
                                        )
                                    }

                                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                        history.forEach { record ->
                                            val catColor = when (record.categoryKey) {
                                                "underweight" -> Color(0xFF60A5FA)
                                                "normal" -> Color(0xFF34D399)
                                                "overweight" -> Color(0xFFFBBF24)
                                                else -> Color(0xFFF87171)
                                            }

                                            val catText = when (record.categoryKey) {
                                                "underweight" -> trans.underweight
                                                "normal" -> trans.normal
                                                "overweight" -> trans.overweight
                                                else -> trans.obese
                                            }

                                            Surface(
                                                modifier = Modifier.fillMaxWidth(),
                                                shape = RoundedCornerShape(16.dp),
                                                color = Color.White
                                            ) {
                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(14.dp),
                                                    horizontalArrangement = Arrangement.SpaceBetween,
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Column {
                                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                                            Box(
                                                                modifier = Modifier
                                                                    .size(8.dp)
                                                                    .background(catColor, CircleShape)
                                                            )
                                                            Spacer(modifier = Modifier.width(6.dp))
                                                            Text(
                                                                text = catText,
                                                                fontWeight = FontWeight.Bold,
                                                                fontSize = 13.sp,
                                                                color = catColor
                                                            )
                                                        }
                                                        Spacer(modifier = Modifier.height(2.dp))
                                                        Text(
                                                            text = "H: ${record.heightStr} • W: ${record.weightStr}",
                                                            fontSize = 12.sp,
                                                            color = BentoMutedText
                                                        )
                                                    }

                                                    Column(horizontalAlignment = Alignment.End) {
                                                        Text(
                                                            text = String.format(Locale.US, "%.1f", record.bmi),
                                                            fontWeight = FontWeight.Black,
                                                            fontSize = 20.sp,
                                                            color = BentoDark
                                                        )
                                                        Text(
                                                            text = record.time,
                                                            fontSize = 10.sp,
                                                            color = BentoMutedText.copy(alpha = 0.6f)
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            "DIET" -> {
                DietScreen(
                    bmiResult = bmiResult ?: (history.firstOrNull()?.bmi),
                    categoryKey = resultCategoryKey.ifEmpty { (history.firstOrNull()?.categoryKey) ?: "normal" },
                    age = age,
                    gender = gender,
                    unitSystem = unitSystem,
                    heightCm = heightCm,
                    heightFeet = heightFeet,
                    heightInches = heightInches,
                    weightKg = weightKg,
                    weightLbs = weightLbs,
                    selectedLangCode = selectedLangCode,
                    userName = userName,
                    trans = trans,
                    innerPadding = innerPadding
                )
            }

            "SETTINGS" -> {
                SettingsScreen(
                    history = history,
                    age = age,
                    gender = gender,
                    unitSystem = unitSystem,
                    heightCm = heightCm,
                    heightFeet = heightFeet,
                    heightInches = heightInches,
                    weightKg = weightKg,
                    weightLbs = weightLbs,
                    selectedLangCode = selectedLangCode,
                    onLangSelected = { selectedLangCode = it },
                    onUnitSystemSelected = { unitSystem = it },
                    isDarkMode = isDarkMode,
                    onDarkModeSelected = { dark ->
                        isDarkMode = dark
                        sharedPreferences.edit().putBoolean("is_dark_mode", dark).apply()
                    },
                    userName = userName,
                    userEmail = userEmail,
                    isLoggedIn = isLoggedIn,
                    userPhotoUrl = userPhotoUrl,
                    onOpenProfileDialog = { showProfileDialog = true },
                    trans = trans,
                    innerPadding = innerPadding
                )
            }
        }

        // Render UserProfileDialog
        val coroutineScope = rememberCoroutineScope()
        UserProfileDialog(
            showDialog = showProfileDialog,
            onDismiss = { showProfileDialog = false },
            currentName = userName,
            currentEmail = userEmail,
            isLoggedIn = isLoggedIn,
            onSaveName = { newName ->
                userName = newName
                sharedPreferences.edit().putString("user_name", newName).apply()
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Profile updated: $newName")
                }
            },
            onGoogleLogin = { name, email ->
                userName = name
                userEmail = email
                isLoggedIn = true
                sharedPreferences.edit()
                    .putString("user_name", name)
                    .putString("user_email", email)
                    .putBoolean("is_logged_in", true)
                    .apply()
                coroutineScope.launch {
                    val msg = getProfileTranslation(selectedLangCode, "login_success")
                    snackbarHostState.showSnackbar(msg)
                }
            },
            onSignOut = {
                userName = ""
                userEmail = ""
                isLoggedIn = false
                sharedPreferences.edit()
                    .putString("user_name", "")
                    .putString("user_email", "")
                    .putBoolean("is_logged_in", false)
                    .apply()
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Logged out successfully.")
                }
            },
            selectedLangCode = selectedLangCode
        )
        }
        }
    }
}

@Composable
fun ButtonStepper(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    testTag: String,
    onClick: () -> Unit
) {
    ButtonStepperBento(
        icon = icon,
        testTag = testTag,
        onClick = onClick,
        accentColor = BentoPurple
    )
}

@Composable
fun ButtonStepperBento(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    testTag: String,
    onClick: () -> Unit,
    accentColor: Color,
    buttonSize: androidx.compose.ui.unit.Dp = 36.dp,
    iconSize: androidx.compose.ui.unit.Dp = 18.dp
) {
    Surface(
        onClick = onClick,
        modifier = Modifier
            .size(buttonSize)
            .clip(CircleShape)
            .testTag(testTag),
        color = BentoCardBg,
        shape = CircleShape
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = accentColor,
                modifier = Modifier.size(iconSize)
            )
        }
    }
}

// Extension properties for consistent M3 typographies
private val MaterialTheme.styleSchemeTitle: androidx.compose.ui.text.TextStyle
    @Composable
    get() = MaterialTheme.typography.titleMedium.copy(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.15.sp,
        color = BentoMutedText
    )

private val MaterialTheme.styleSchemeSubtitle: androidx.compose.ui.text.TextStyle
    @Composable
    get() = MaterialTheme.typography.titleSmall.copy(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.1.sp,
        color = BentoMutedText
    )

@Composable
fun BmiTrendChart(
    history: List<BmiRecord>,
    trans: Translation,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = BentoGrayBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.TrendingUp,
                        contentDescription = trans.trendTitle,
                        tint = BentoPurple,
                        modifier = Modifier.size(20.dp)
                    )
                    Column {
                        Text(
                            text = trans.trendTitle.uppercase(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = BentoMutedText,
                            letterSpacing = 1.1.sp
                        )
                        Text(
                            text = trans.trendSubtitle,
                            fontSize = 11.sp,
                            color = BentoMutedText.copy(alpha = 0.7f)
                        )
                    }
                }
            }

            if (history.size < 2) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(BentoCardBg, RoundedCornerShape(24.dp))
                        .padding(20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = trans.trendPlaceholder,
                        fontSize = 13.sp,
                        color = BentoMutedText,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        lineHeight = 18.sp
                    )
                }
            } else {
                val sortedRecords = remember(history) { history.reversed() } // Oldest to newest
                val bmis = sortedRecords.map { it.bmi }
                val minY = bmis.minOrNull() ?: 18.5f
                val maxY = bmis.maxOrNull() ?: 24.9f
                
                val rangeY = (maxY - minY).coerceAtLeast(3f)
                val chartMinY = minY - (rangeY * 0.15f)
                val chartMaxY = maxY + (rangeY * 0.15f)
                val finalRangeY = chartMaxY - chartMinY

                val cardBg = BentoCardBg
                val chartColor = BentoPurple

                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(cardBg, RoundedCornerShape(16.dp))
                        .padding(horizontal = 16.dp, vertical = 20.dp)
                ) {
                    val width = constraints.maxWidth.toFloat()
                    val height = constraints.maxHeight.toFloat()

                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val numPoints = sortedRecords.size
                        val stepX = width / (numPoints - 1).coerceAtLeast(1)

                        val numGridLines = 4
                        for (i in 0 until numGridLines) {
                            val ratio = i.toFloat() / (numGridLines - 1)
                            val y = height * ratio
                            drawLine(
                                color = Color(0xFFE2E8F0),
                                start = Offset(0f, y),
                                end = Offset(width, y),
                                strokeWidth = 1.dp.toPx()
                            )
                        }

                        val points = sortedRecords.mapIndexed { index, record ->
                            val x = index * stepX
                            val ratioY = if (finalRangeY > 0) (record.bmi - chartMinY) / finalRangeY else 0.5f
                            val y = height - (ratioY * height)
                            Offset(x, y)
                        }

                        val fillPath = Path().apply {
                            if (points.isNotEmpty()) {
                                moveTo(points[0].x, height)
                                points.forEach { point ->
                                    lineTo(point.x, point.y)
                                }
                                lineTo(points.last().x, height)
                                close()
                            }
                        }
                        drawPath(
                            path = fillPath,
                            brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                                colors = listOf(
                                    chartColor.copy(alpha = 0.3f),
                                    Color.Transparent
                                )
                            )
                        )

                        val linePath = Path().apply {
                            if (points.isNotEmpty()) {
                                moveTo(points[0].x, points[0].y)
                                for (i in 1 until points.size) {
                                    lineTo(points[i].x, points[i].y)
                                }
                            }
                        }
                        drawPath(
                            path = linePath,
                            color = chartColor,
                            style = Stroke(
                                width = 3.dp.toPx(),
                                join = StrokeJoin.Round,
                                cap = StrokeCap.Round
                            )
                        )

                        points.forEachIndexed { index, point ->
                            val record = sortedRecords[index]
                            val pointColor = when (record.categoryKey) {
                                "underweight" -> Color(0xFF60A5FA)
                                "normal" -> Color(0xFF34D399)
                                "overweight" -> Color(0xFFFBBF24)
                                else -> Color(0xFFF87171)
                            }

                            drawCircle(
                                color = Color.White,
                                radius = 6.dp.toPx(),
                                center = point
                            )
                            drawCircle(
                                color = pointColor,
                                radius = 4.dp.toPx(),
                                center = point
                            )
                        }
                    }

                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = String.format(Locale.US, "%.1f", chartMaxY),
                            fontSize = 9.sp,
                            color = BentoMutedText.copy(alpha = 0.5f),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.TopStart)
                        )
                        Text(
                            text = String.format(Locale.US, "%.1f", chartMinY),
                            fontSize = 9.sp,
                            color = BentoMutedText.copy(alpha = 0.5f),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.BottomStart)
                        )
                    }
                }
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    sortedRecords.forEachIndexed { idx, record ->
                        val shouldShowLabel = idx == 0 || idx == sortedRecords.lastIndex || (sortedRecords.size > 2 && idx == sortedRecords.size / 2)
                        if (shouldShowLabel) {
                            Text(
                                text = record.time,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = BentoMutedText.copy(alpha = 0.6f)
                            )
                        } else {
                            Spacer(modifier = Modifier.width(1.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AiPredictiveCard(
    history: List<BmiRecord>,
    age: Int,
    gender: Gender,
    unitSystem: UnitSystem,
    heightCm: Float,
    heightFeet: Int,
    heightInches: Int,
    weightKg: Float,
    weightLbs: Float,
    trans: Translation,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val localModel = remember(history) { trainLinearRegression(history) }
    
    var aiForecastResult by remember { mutableStateOf<PredictionResult?>(null) }
    var isTraining by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = BentoDark),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = trans.predictionTitle,
                        tint = BentoLilac2,
                        modifier = Modifier.size(22.dp)
                    )
                    Column {
                        Text(
                            text = trans.predictionTitle.uppercase(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = Color.White,
                            letterSpacing = 1.1.sp
                        )
                        Text(
                            text = trans.predictionSubtitle,
                            fontSize = 11.sp,
                            color = Color(0xFFCAC4D0)
                        )
                    }
                }
            }

            if (history.size < 2) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = trans.trendPlaceholder,
                        fontSize = 12.sp,
                        color = Color(0xFFE6E1E5),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        lineHeight = 17.sp
                    )
                }
            } else {
                localModel?.let { model ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White.copy(alpha = 0.05f), RoundedCornerShape(20.dp))
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = trans.localModel.uppercase(),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = BentoLilac2,
                            letterSpacing = 1.2.sp
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(Color.White.copy(alpha = 0.04f), RoundedCornerShape(12.dp))
                                    .padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = trans.localSlopeText,
                                    fontSize = 10.sp,
                                    color = Color(0xFFCAC4D0)
                                )
                                Text(
                                    text = String.format(Locale.US, "%+.2f", model.slope),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(Color.White.copy(alpha = 0.04f), RoundedCornerShape(12.dp))
                                    .padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = trans.localFitText,
                                    fontSize = 10.sp,
                                    color = Color(0xFFCAC4D0)
                                )
                                Text(
                                    text = String.format(Locale.US, "%.0f%%", model.rSquared * 100f),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White.copy(alpha = 0.04f), RoundedCornerShape(12.dp))
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = trans.localPredictText,
                                fontSize = 11.sp,
                                color = Color(0xFFE6E1E5)
                            )
                            Text(
                                text = String.format(Locale.US, "%.1f", model.nextPrediction),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black,
                                color = BentoLilac2
                            )
                        }
                    }
                }

                Button(
                    onClick = {
                        isTraining = true
                        coroutineScope.launch {
                            val key = BuildConfig.GEMINI_API_KEY
                            val hStr = if (unitSystem == UnitSystem.METRIC) "${heightCm.roundToInt()} cm" else "$heightFeet'$heightInches\""
                            val wStr = if (unitSystem == UnitSystem.METRIC) "${weightKg.roundToInt()} kg" else "${weightLbs.roundToInt()} lbs"
                            
                            val result = fetchGeminiPrediction(
                                apiKey = key,
                                history = history,
                                age = age,
                                gender = gender.name,
                                heightStr = hStr,
                                weightStr = wStr
                            )
                            aiForecastResult = result
                            isTraining = false
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BentoPurple,
                        contentColor = Color.White
                    ),
                    enabled = !isTraining
                ) {
                    if (isTraining) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(16.dp),
                                color = Color.White,
                                strokeWidth = 2.dp
                            )
                            Text(text = trans.aiTraining, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }
                    } else {
                        Text(text = trans.aiTrainBtn.uppercase(), fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.1.sp)
                    }
                }

                aiForecastResult?.let { result ->
                    if (result.success) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White.copy(alpha = 0.05f), RoundedCornerShape(20.dp))
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = trans.predictionResult.uppercase(),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = BentoLilac2,
                                    letterSpacing = 1.2.sp
                                )

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    val confColor = when {
                                        result.confidencePercent >= 80 -> Color(0xFF34D399)
                                        result.confidencePercent >= 50 -> Color(0xFFFBBF24)
                                        else -> Color(0xFFF87171)
                                    }
                                    Box(modifier = Modifier.size(6.dp).background(confColor, CircleShape))
                                    Text(
                                        text = "${trans.confidence}: ${result.confidenceText} (${result.confidencePercent}%)",
                                        color = Color(0xFFCAC4D0),
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = trans.predictionBmi,
                                    fontSize = 12.sp,
                                    color = Color(0xFFE6E1E5)
                                )
                                Text(
                                    text = String.format(Locale.US, "%.1f", result.predictedBmi),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color.White
                                )
                            }

                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = trans.predictionTrajectory,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFCAC4D0)
                                )
                                Text(
                                    text = result.trajectory,
                                    fontSize = 12.sp,
                                    color = Color.White,
                                    lineHeight = 16.sp
                                )
                            }

                            if (result.recommendations.isNotEmpty()) {
                                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Text(
                                        text = trans.recsTitle.uppercase(),
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = BentoLilac2,
                                        letterSpacing = 1.1.sp
                                    )
                                    result.recommendations.forEach { rec ->
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            Text(text = "•", color = BentoLilac2, fontSize = 12.sp)
                                            Text(text = rec, color = Color(0xFFE6E1E5), fontSize = 11.sp, lineHeight = 15.sp)
                                        }
                                    }
                                }
                            }

                            if (result.obstacles.isNotEmpty()) {
                                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Text(
                                        text = trans.obstaclesTitle.uppercase(),
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFF87171),
                                        letterSpacing = 1.1.sp
                                    )
                                    result.obstacles.forEach { obs ->
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            Text(text = "•", color = Color(0xFFF87171), fontSize = 12.sp)
                                            Text(text = obs, color = Color(0xFFE6E1E5), fontSize = 11.sp, lineHeight = 15.sp)
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        Text(
                            text = result.rawError ?: "Error generating forecast",
                            color = Color(0xFFF87171),
                            fontSize = 11.sp,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                } ?: run {
                    Text(
                        text = trans.noModelResult,
                        fontSize = 11.sp,
                        color = Color(0xFFCAC4D0),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

data class RegressionModel(
    val slope: Float,
    val intercept: Float,
    val rSquared: Float,
    val nextPrediction: Float
)

fun trainLinearRegression(history: List<BmiRecord>): RegressionModel? {
    if (history.size < 2) return null
    val sorted = history.reversed()
    val n = sorted.size
    
    var sumX = 0f
    var sumY = 0f
    var sumXY = 0f
    var sumXX = 0f
    
    sorted.forEachIndexed { index, record ->
        val x = (index + 1).toFloat()
        val y = record.bmi
        sumX += x
        sumY += y
        sumXY += x * y
        sumXX += x * x
    }
    
    val meanX = sumX / n
    val meanY = sumY / n
    val denominator = n * sumXX - sumX * sumX
    if (denominator == 0f) return null
    
    val slope = (n * sumXY - sumX * sumY) / denominator
    val intercept = meanY - slope * meanX
    
    var totalSumOfSquares = 0f
    var residualSumOfSquares = 0f
    sorted.forEachIndexed { index, record ->
        val x = (index + 1).toFloat()
        val y = record.bmi
        val predictedY = slope * x + intercept
        totalSumOfSquares += (y - meanY) * (y - meanY)
        residualSumOfSquares += (y - predictedY) * (y - predictedY)
    }
    
    val rSquared = if (totalSumOfSquares > 0f) 1f - (residualSumOfSquares / totalSumOfSquares) else 1f
    val nextPrediction = slope * (n + 1) + intercept
    
    return RegressionModel(
        slope = slope,
        intercept = intercept,
        rSquared = rSquared.coerceIn(0f, 1f),
        nextPrediction = nextPrediction
    )
}

data class PredictionResult(
    val success: Boolean,
    val predictedBmi: Float,
    val trajectory: String,
    val confidenceText: String,
    val confidencePercent: Int,
    val recommendations: List<String>,
    val obstacles: List<String>,
    val rawError: String? = null
)

suspend fun fetchGeminiPrediction(
    apiKey: String,
    history: List<BmiRecord>,
    age: Int,
    gender: String,
    heightStr: String,
    weightStr: String
): PredictionResult = withContext(Dispatchers.IO) {
    val prompt = """
        You are an advanced AI Health Forecaster and Predictive Model.
        Analyze the following historical BMI trend for a user:
        - Age: $age
        - Gender: $gender
        - Current Height: $heightStr
        - Current Weight: $weightStr
        
        Historical BMI Logs (oldest to newest):
        ${history.reversed().mapIndexed { i, r -> "Log #${i+1}: BMI = ${String.format(Locale.US, "%.1f", r.bmi)} (Weight: ${r.weightStr}, Height: ${r.heightStr})" }.joinToString("\n")}
        
        Based on this data, train your predictive model to forecast:
        1. Projected BMI in 4 weeks
        2. Expected weight trajectory (direction, rate of change)
        3. Confidence level (Low/Medium/High) with a percentage
        4. Structured, personalized, and actionable steps to reach a healthy weight (BMI 18.5 - 24.9).
        5. Key obstacles to avoid.
        
        Respond ONLY with a valid JSON object matching this exact schema:
        {
          "predictedBmi": 24.5,
          "trajectory": "Gradual reduction towards healthy range. Estimated rate: -0.2 BMI/week.",
          "confidenceText": "High",
          "confidencePercent": 85,
          "recommendations": [
            "Actionable step 1",
            "Actionable step 2",
            "Actionable step 3"
          ],
          "obstacles": [
            "Obstacle 1",
            "Obstacle 2"
          ]
        }
        Do not wrap in ```json or markdown. Provide only the raw JSON.
    """.trimIndent()

    val client = OkHttpClient.Builder()
        .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
        .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
        .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
        .build()

    val url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey"

    val requestJson = JSONObject().apply {
        put("contents", JSONArray().apply {
            put(JSONObject().apply {
                put("parts", JSONArray().apply {
                    put(JSONObject().apply {
                        put("text", prompt)
                    })
                })
            })
        })
    }

    val requestBody = requestJson.toString().toRequestBody("application/json; charset=utf-8".toMediaType())
    val request = Request.Builder()
        .url(url)
        .post(requestBody)
        .build()

    try {
        val response = client.newCall(request).execute()
        val responseBodyString = response.body?.string() ?: ""
        
        if (response.isSuccessful && responseBodyString.isNotEmpty()) {
            val resObj = JSONObject(responseBodyString)
            val candidates = resObj.getJSONArray("candidates")
            val content = candidates.getJSONObject(0).getJSONObject("content")
            val parts = content.getJSONArray("parts")
            var rawText = parts.getJSONObject(0).getString("text").trim()
            
            if (rawText.startsWith("```json")) {
                rawText = rawText.substring(7)
            }
            if (rawText.startsWith("```")) {
                rawText = rawText.substring(3)
            }
            if (rawText.endsWith("```")) {
                rawText = rawText.substring(0, rawText.length - 3)
            }
            rawText = rawText.trim()

            val predJson = JSONObject(rawText)
            val recommendationsArray = predJson.optJSONArray("recommendations")
            val recList = mutableListOf<String>()
            if (recommendationsArray != null) {
                for (i in 0 until recommendationsArray.length()) {
                    recList.add(recommendationsArray.getString(i))
                }
            }
            
            val obstaclesArray = predJson.optJSONArray("obstacles")
            val obsList = mutableListOf<String>()
            if (obstaclesArray != null) {
                for (i in 0 until obstaclesArray.length()) {
                    obsList.add(obstaclesArray.getString(i))
                }
            }

            PredictionResult(
                success = true,
                predictedBmi = predJson.optDouble("predictedBmi", 0.0).toFloat(),
                trajectory = predJson.optString("trajectory", ""),
                confidenceText = predJson.optString("confidenceText", "Medium"),
                confidencePercent = predJson.optInt("confidencePercent", 70),
                recommendations = recList,
                obstacles = obsList,
                rawError = null
            )
        } else {
            PredictionResult(
                success = false,
                predictedBmi = 0f,
                trajectory = "",
                confidenceText = "",
                confidencePercent = 0,
                recommendations = emptyList(),
                obstacles = emptyList(),
                rawError = "HTTP ${response.code}: ${response.message}"
            )
        }
    } catch (e: Exception) {
        PredictionResult(
            success = false,
            predictedBmi = 0f,
            trajectory = "",
            confidenceText = "",
            confidencePercent = 0,
            recommendations = emptyList(),
            obstacles = emptyList(),
            rawError = e.localizedMessage ?: "Connection error"
        )
    }
}

@Composable
fun DietScreen(
    bmiResult: Float?,
    categoryKey: String,
    age: Int,
    gender: Gender,
    unitSystem: UnitSystem,
    heightCm: Float,
    heightFeet: Int,
    heightInches: Int,
    weightKg: Float,
    weightLbs: Float,
    selectedLangCode: String,
    userName: String,
    trans: Translation,
    innerPadding: androidx.compose.foundation.layout.PaddingValues
) {
    val coroutineScope = rememberCoroutineScope()
    var isGenerating by remember { mutableStateOf(false) }
    var aiDietPlan by remember { mutableStateOf<AiDietPlanResult?>(null) }

    // Fallback meal plans based on category
    val localPlan = remember(categoryKey) {
        when (categoryKey) {
            "underweight" -> DefaultDietPlan(
                targetCalories = 2400,
                protein = "130g",
                carbs = "300g",
                fats = "80g",
                breakfast = "Peanut butter & honey toast with banana, plus whole milk.",
                lunch = "Turkey, Swiss cheese, and avocado sandwich with mixed nuts.",
                dinner = "Beef stir-fry with broccoli, carrots, and jasmine rice.",
                snacks = "Greek yogurt with honey, granola, and chia seeds."
            )
            "overweight", "obese" -> DefaultDietPlan(
                targetCalories = 1600,
                protein = "120g",
                carbs = "150g",
                fats = "55g",
                breakfast = "Egg white omelette with spinach, bell peppers, and avocado.",
                lunch = "Grilled chicken breast over a large green salad with olive oil dressing.",
                dinner = "Baked salmon with roasted asparagus and a small sweet potato.",
                snacks = "Mixed berries with a handful of walnuts."
            )
            else -> DefaultDietPlan(
                targetCalories = 2000,
                protein = "110g",
                carbs = "230g",
                fats = "65g",
                breakfast = "Two scrambled eggs with spinach and whole wheat toast.",
                lunch = "Quinoa bowl with chickpeas, cucumbers, cherry tomatoes, and feta.",
                dinner = "Baked salmon with roasted sweet potatoes and steamed broccoli.",
                snacks = "Baby carrots with hummus or a medium apple."
            )
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(BentoBg),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 16.dp,
            bottom = 40.dp
        )
    ) {
        // Hero Header Bento Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = BentoDark),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "🥗 NUTRITION PLANNER",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = BentoLilac2,
                        letterSpacing = 1.5.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = when (selectedLangCode) {
                            "fr" -> "Votre Guide Nutritionnel"
                            "ha" -> "Jagoran Abincinku"
                            "ig" -> "Ntuziaka Nri Gị"
                            "yo" -> "Eto Ounjẹ Rẹ"
                            else -> "Your Personalized Guide"
                        },
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = when (selectedLangCode) {
                            "fr" -> "Des repas optimisés pour un IMC sain de 18.5 à 24.9"
                            "ha" -> "Abinci da aka inganta don samun lafiyayyan BMI na 18.5 - 24.9"
                            "ig" -> "Nri kachasị mma maka BMI dị mma nke 18.5 - 24.9"
                            "yo" -> "Ounjẹ to dara fun BMI to ni ilera ti 18.5 - 24.9"
                            else -> "Meals optimized to help you maintain a healthy BMI of 18.5 - 24.9"
                        },
                        fontSize = 12.sp,
                        color = Color(0xFFCAC4D0)
                    )
                }
            }
        }

        // Calorie and Macros Bento Box
        item {
            val calories = aiDietPlan?.dailyCalories ?: localPlan.targetCalories
            val protein = aiDietPlan?.macros?.protein ?: localPlan.protein
            val carbs = aiDietPlan?.macros?.carbs ?: localPlan.carbs
            val fats = aiDietPlan?.macros?.fats ?: localPlan.fats

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Calorie Target Cell (Left)
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(140.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = BentoLilac1),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "CALORIE TARGET",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = BentoPurple,
                            letterSpacing = 1.1.sp
                        )
                        Column {
                            Text(
                                text = "$calories",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Light,
                                color = BentoDark
                            )
                            Text(
                                text = "kcal / day",
                                fontSize = 12.sp,
                                color = BentoPurple.copy(alpha = 0.7f)
                            )
                        }
                    }
                }

                // Macros Cell (Right)
                Card(
                    modifier = Modifier
                        .weight(1.2f)
                        .height(140.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = BentoGrayBg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "MACRONUTRIENTS",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = BentoMutedText,
                            letterSpacing = 1.1.sp
                        )
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("🥩 Protein:", fontSize = 11.sp, color = BentoMutedText)
                                Text(protein, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BentoDark)
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("🍞 Carbs:", fontSize = 11.sp, color = BentoMutedText)
                                Text(carbs, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BentoDark)
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("🥑 Fats:", fontSize = 11.sp, color = BentoMutedText)
                                Text(fats, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BentoDark)
                            }
                        }
                    }
                }
            }
        }

        // Generate AI Diet Button
        item {
            Button(
                onClick = {
                    isGenerating = true
                    coroutineScope.launch {
                        val key = BuildConfig.GEMINI_API_KEY
                        val hStr = if (unitSystem == UnitSystem.METRIC) "${heightCm.roundToInt()} cm" else "$heightFeet'$heightInches\""
                        val wStr = if (unitSystem == UnitSystem.METRIC) "${weightKg.roundToInt()} kg" else "${weightLbs.roundToInt()} lbs"
                        
                        aiDietPlan = fetchGeminiDiet(
                            apiKey = key,
                            age = age,
                            gender = gender.name,
                            heightStr = hStr,
                            weightStr = wStr,
                            bmi = bmiResult ?: 22f,
                            category = categoryKey,
                            userName = userName
                        )
                        isGenerating = false
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BentoPurple,
                    contentColor = Color.White
                ),
                enabled = !isGenerating
            ) {
                if (isGenerating) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(16.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                        Text("Customizing Meal Plan...", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    }
                } else {
                    Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "GENERATE CUSTOM AI DIET",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.1.sp
                    )
                }
            }
        }

        // Meal Cards List (Breakfast, Lunch, Dinner, Snack)
        val breakfast = aiDietPlan?.breakfast ?: localPlan.breakfast
        val lunch = aiDietPlan?.lunch ?: localPlan.lunch
        val dinner = aiDietPlan?.dinner ?: localPlan.dinner
        val snacks = aiDietPlan?.snacks ?: localPlan.snacks

        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                MealRow(emoji = "🍳", title = "Breakfast", description = breakfast)
                MealRow(emoji = "🥪", title = "Lunch", description = lunch)
                MealRow(emoji = "🍗", title = "Dinner", description = dinner)
                MealRow(emoji = "🍎", title = "Snacks", description = snacks)
            }
        }
    }
}

@Composable
fun MealRow(emoji: String, title: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = BentoCardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BentoBorder.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(BentoGrayBg, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(emoji, fontSize = 22.sp)
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title.uppercase(),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = BentoMutedText,
                    letterSpacing = 1.1.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 13.sp,
                    color = BentoDark,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

data class DefaultDietPlan(
    val targetCalories: Int,
    val protein: String,
    val carbs: String,
    val fats: String,
    val breakfast: String,
    val lunch: String,
    val dinner: String,
    val snacks: String
)

data class AiDietPlanResult(
    val dailyCalories: Int,
    val macros: AiMacros,
    val breakfast: String,
    val lunch: String,
    val dinner: String,
    val snacks: String
)

data class AiMacros(
    val protein: String,
    val carbs: String,
    val fats: String
)

@Composable
fun SettingsScreen(
    history: MutableList<BmiRecord>,
    age: Int,
    gender: Gender,
    unitSystem: UnitSystem,
    heightCm: Float,
    heightFeet: Int,
    heightInches: Int,
    weightKg: Float,
    weightLbs: Float,
    selectedLangCode: String,
    onLangSelected: (String) -> Unit,
    onUnitSystemSelected: (UnitSystem) -> Unit,
    isDarkMode: Boolean,
    onDarkModeSelected: (Boolean) -> Unit,
    userName: String,
    userEmail: String,
    isLoggedIn: Boolean,
    userPhotoUrl: String,
    onOpenProfileDialog: () -> Unit,
    trans: Translation,
    innerPadding: androidx.compose.foundation.layout.PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(BentoBg),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 16.dp,
            bottom = 40.dp
        )
    ) {
        // Settings Header Bento Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = BentoDark),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "⚙️ SETTINGS & PREFERENCES",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = BentoLilac2,
                        letterSpacing = 1.5.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Customize App Settings",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }
            }
        }

        // 1.5 User Profile & Google Authentication Bento Card
        item {
            val defaultName = getProfileTranslation(selectedLangCode, "guest")

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOpenProfileDialog() }
                    .testTag("settings_profile_card"),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = BentoCardBg),
                border = androidx.compose.foundation.BorderStroke(1.dp, BentoBorder.copy(alpha = 0.5f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Profile Icon/Image
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(BentoLightPurple, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (userName.isNotBlank()) userName.trim().take(1).uppercase() else "👤",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = BentoDeepPurple
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (userName.isNotBlank()) userName else defaultName,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = BentoDark
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = if (isLoggedIn) userEmail else "Local Mode / Not Logged In",
                            fontSize = 12.sp,
                            color = BentoMutedText
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(BentoGrayBg)
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = if (isLoggedIn) "GOOGLE" else "OFFLINE",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = BentoPurple,
                            letterSpacing = 1.sp
                        )
                    }
                }
            }
        }

        // Profile Quick Stats Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = BentoGrayBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "👤 USER PROFILE STATS",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = BentoMutedText,
                        letterSpacing = 1.1.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ProfileStatBadge(label = "Age", value = "$age yrs", modifier = Modifier.weight(1f))
                        ProfileStatBadge(label = "Gender", value = gender.name, modifier = Modifier.weight(1f))
                        val currentWeight = if (unitSystem == UnitSystem.METRIC) "${weightKg.roundToInt()} kg" else "${weightLbs.roundToInt()} lbs"
                        ProfileStatBadge(label = "Weight", value = currentWeight, modifier = Modifier.weight(1.2f))
                    }
                }
            }
        }

        // Language Preference Bento Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = BentoGrayBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = trans.languageLabel + " / Yanayin Harshe",
                        fontWeight = FontWeight.Bold,
                        color = BentoMutedText,
                        modifier = Modifier.padding(bottom = 12.dp),
                        fontSize = 13.sp
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(languagesList) { lang ->
                            val isSelected = selectedLangCode == lang.code
                            val containerColor by animateColorAsState(
                                targetValue = if (isSelected) BentoPurple else Color.White,
                                label = "langBoxColor"
                            )
                            val textColor = if (isSelected) Color.White else BentoMutedText

                            Surface(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .clickable { onLangSelected(lang.code) }
                                    .testTag("settings_lang_chip_${lang.code}"),
                                color = containerColor,
                                border = if (!isSelected) androidx.compose.foundation.BorderStroke(1.dp, BentoBorder) else null,
                                shape = RoundedCornerShape(20.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = lang.flag, modifier = Modifier.padding(end = 6.dp))
                                    Text(
                                        text = lang.name,
                                        color = textColor,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Metric / Imperial Unit Tab Row
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = BentoGrayBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "UNIT SYSTEM MEASUREMENTS",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = BentoMutedText,
                        letterSpacing = 1.1.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        UnitSystem.values().forEach { system ->
                            val isSelected = unitSystem == system
                            val chipBg by animateColorAsState(
                                targetValue = if (isSelected) Color.White else Color.Transparent,
                                label = "unitBgColor"
                            )
                            val contentColor = if (isSelected) BentoPurple else BentoMutedText

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(18.dp))
                                    .background(chipBg)
                                    .clickable { onUnitSystemSelected(system) }
                                    .padding(vertical = 12.dp)
                                    .testTag("settings_unit_tab_${system.name.lowercase()}"),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (system == UnitSystem.METRIC) trans.metric else trans.imperial,
                                    color = contentColor,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        // Dark Mode Bento Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = BentoGrayBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "APPEARANCE MODE",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = BentoMutedText,
                        letterSpacing = 1.1.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        // Light Mode Option
                        val isLightSelected = !isDarkMode
                        val lightChipBg by animateColorAsState(
                            targetValue = if (isLightSelected) BentoCardBg else Color.Transparent,
                            label = "lightBgColor"
                        )
                        val lightContentColor = if (isLightSelected) BentoPurple else BentoMutedText

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(18.dp))
                                .background(lightChipBg)
                                .clickable { onDarkModeSelected(false) }
                                .padding(vertical = 12.dp)
                                .testTag("settings_theme_light"),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text("☀️", fontSize = 16.sp)
                                Text(
                                    text = "Light",
                                    color = lightContentColor,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                        }

                        // Dark Mode Option
                        val darkChipBg by animateColorAsState(
                            targetValue = if (isDarkMode) BentoCardBg else Color.Transparent,
                            label = "darkBgColor"
                        )
                        val darkContentColor = if (isDarkMode) BentoPurple else BentoMutedText

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(18.dp))
                                .background(darkChipBg)
                                .clickable { onDarkModeSelected(true) }
                                .padding(vertical = 12.dp)
                                .testTag("settings_theme_dark"),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text("🌙", fontSize = 16.sp)
                                Text(
                                    text = "Dark",
                                    color = darkContentColor,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        // WHO Standard BMI Reference Table
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = BentoCardBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BentoBorder.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "📊 WHO BMI STANDARDS REFERENCE",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = BentoMutedText,
                        letterSpacing = 1.1.sp
                    )
                    
                    WhoRow(range = "< 18.5", label = trans.underweight, color = Color(0xFF60A5FA))
                    WhoRow(range = "18.5 – 24.9", label = trans.normal, color = Color(0xFF34D399))
                    WhoRow(range = "25.0 – 29.9", label = trans.overweight, color = Color(0xFFFBBF24))
                    WhoRow(range = "≥ 30.0", label = trans.obese, color = Color(0xFFF87171))
                }
            }
        }

        // Clean up history card
        if (history.isNotEmpty()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = BentoCardBg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.4f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { history.clear() }
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(MaterialTheme.colorScheme.error.copy(alpha = 0.1f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("🗑️", fontSize = 18.sp)
                        }
                        Column {
                            Text(
                                text = "DELETE ALL DATA",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.error,
                                letterSpacing = 1.1.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Permanently clear all saved BMI calculation history.",
                                fontSize = 12.sp,
                                color = BentoMutedText
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileStatBadge(label: String, value: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(label.uppercase(), fontSize = 9.sp, color = BentoMutedText, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, fontSize = 14.sp, color = BentoDark, fontWeight = FontWeight.ExtraBold)
        }
    }
}

@Composable
fun WhoRow(range: String, label: String, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(10.dp).background(color, CircleShape))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = label, fontSize = 13.sp, color = BentoDark, fontWeight = FontWeight.Medium)
        }
        Text(text = range, fontSize = 13.sp, color = BentoMutedText, fontWeight = FontWeight.Bold)
    }
}

suspend fun fetchGeminiDiet(
    apiKey: String,
    age: Int,
    gender: String,
    heightStr: String,
    weightStr: String,
    bmi: Float,
    category: String,
    userName: String = ""
): AiDietPlanResult = withContext(Dispatchers.IO) {
    val prompt = """
        You are an elite, certified health and clinical nutrition expert.
        Generate a highly personalized, delicious, and medically optimized daily meal plan.
        
        User details:
        ${if (userName.isNotBlank()) "- Name: ${"$"}" + "userName" else ""}
        - Age: ${"$"}age
        - Gender: ${"$"}gender
        - Height: ${"$"}heightStr
        - Weight: ${"$"}weightStr
        - Current BMI: ${"$"}{String.format(Locale.US, "%.1f", bmi)} (${"$"}category)
        
        Generate the daily meal targets and plan:
        1. Daily calorie target budget
        2. Exact macronutrient distribution targets (Protein, Carbs, Fats as string like '120g')
        3. Meal plan for breakfast, lunch, dinner, and snacks.
        
        Respond ONLY with a valid JSON object matching this exact schema:
        {
          "dailyCalories": 1800,
          "macros": {
            "protein": "120g",
            "carbs": "160g",
            "fats": "55g"
          },
          "breakfast": "Egg white wrap with baby spinach, tomatoes, and 1/4 sliced avocado.",
          "lunch": "Grilled tuna fillet over a mixed green bed with cold-pressed olive oil.",
          "dinner": "Lemon herb chicken breast with roasted Brussels sprouts and yellow squash.",
          "snacks": "A small bowl of fresh raspberries and 10 almonds."
        }
        Do not wrap in ```json or markdown. Provide only the raw JSON.
    """.trimIndent()

    val client = OkHttpClient.Builder()
        .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
        .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
        .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
        .build()

    val url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=${"$"}apiKey"

    val requestJson = JSONObject().apply {
        put("contents", JSONArray().apply {
            put(JSONObject().apply {
                put("parts", JSONArray().apply {
                    put(JSONObject().apply {
                        put("text", prompt)
                    })
                })
            })
        })
    }

    val requestBody = requestJson.toString().toRequestBody("application/json; charset=utf-8".toMediaType())
    val request = Request.Builder()
        .url(url)
        .post(requestBody)
        .build()

    try {
        val response = client.newCall(request).execute()
        val responseBodyString = response.body?.string() ?: ""
        
        if (response.isSuccessful && responseBodyString.isNotEmpty()) {
            val resObj = JSONObject(responseBodyString)
            val candidates = resObj.getJSONArray("candidates")
            val content = candidates.getJSONObject(0).getJSONObject("content")
            val parts = content.getJSONArray("parts")
            var rawText = parts.getJSONObject(0).getString("text").trim()
            
            if (rawText.startsWith("```json")) {
                rawText = rawText.substring(7)
            }
            if (rawText.startsWith("```")) {
                rawText = rawText.substring(3)
            }
            if (rawText.endsWith("```")) {
                rawText = rawText.substring(0, rawText.length - 3)
            }
            rawText = rawText.trim()

            val dietJson = JSONObject(rawText)
            val macrosJson = dietJson.getJSONObject("macros")

            AiDietPlanResult(
                dailyCalories = dietJson.optInt("dailyCalories", 2000),
                macros = AiMacros(
                    protein = macrosJson.optString("protein", "110g"),
                    carbs = macrosJson.optString("carbs", "200g"),
                    fats = macrosJson.optString("fats", "60g")
                ),
                breakfast = dietJson.optString("breakfast", ""),
                lunch = dietJson.optString("lunch", ""),
                dinner = dietJson.optString("dinner", ""),
                snacks = dietJson.optString("snacks", "")
            )
        } else {
            AiDietPlanResult(
                dailyCalories = 2000,
                macros = AiMacros("110g", "200g", "60g"),
                breakfast = "Error calling AI. Enjoy: Two eggs with toast",
                lunch = "Greek chicken wrap",
                dinner = "Baked fish with spinach",
                snacks = "Fruit and nuts"
            )
        }
    } catch (e: Exception) {
        AiDietPlanResult(
            dailyCalories = 2000,
            macros = AiMacros("110g", "200g", "60g"),
            breakfast = "Error: ${"$"}{e.localizedMessage}. Fallback: Eggs and spinach",
            lunch = "Mixed greens tuna salad",
            dinner = "Pan-seared salmon with broccoli",
            snacks = "Carrots and hummus"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    currentName: String,
    currentEmail: String,
    isLoggedIn: Boolean,
    onSaveName: (String) -> Unit,
    onGoogleLogin: (String, String) -> Unit,
    onSignOut: () -> Unit,
    selectedLangCode: String
) {
    if (!showDialog) return

    var editedName by remember(currentName) { mutableStateOf(currentName) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var showAccountPicker by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = BentoBg)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = getProfileTranslation(selectedLangCode, "profile_title").uppercase(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = BentoMutedText,
                        letterSpacing = 1.1.sp
                    )
                    Text(
                        text = "✕",
                        modifier = Modifier
                            .clickable { onDismiss() }
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        color = BentoMutedText
                    )
                }

                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(BentoLightPurple, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (editedName.isNotBlank()) editedName.trim().take(1).uppercase() else "👤",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = BentoDeepPurple
                    )
                }

                if (isLoggedIn) {
                    Text(
                        text = "Google Account Active",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF34D399),
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = currentEmail,
                        fontSize = 13.sp,
                        color = BentoMutedText
                    )
                }

                OutlinedTextField(
                    value = editedName,
                    onValueChange = { editedName = it },
                    label = { Text(getProfileTranslation(selectedLangCode, "user_name")) },
                    placeholder = { Text(getProfileTranslation(selectedLangCode, "enter_name_hint")) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("profile_name_input"),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = BentoPurple,
                        unfocusedIndicatorColor = BentoBorder
                    )
                )

                Button(
                    onClick = {
                        onSaveName(editedName)
                        onDismiss()
                    },
                    modifier = Modifier.fillMaxWidth().testTag("profile_save_button"),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BentoPurple)
                ) {
                    Text(
                        text = getProfileTranslation(selectedLangCode, "save").uppercase(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        letterSpacing = 1.sp
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f).height(1.dp).background(BentoBorder.copy(alpha = 0.5f)))
                    Text(
                        text = " OR ",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = BentoMutedText.copy(alpha = 0.5f),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Box(modifier = Modifier.weight(1f).height(1.dp).background(BentoBorder.copy(alpha = 0.5f)))
                }

                if (!isLoggedIn) {
                    Button(
                        onClick = {
                            val credentialManager = CredentialManager.create(context)
                            coroutineScope.launch {
                                try {
                                    val googleIdOption = GetGoogleIdOption.Builder()
                                        .setFilterByAuthorizedAccounts(false)
                                        .setServerClientId("dummy-client-id-for-real-compilation")
                                        .setAutoSelectEnabled(true)
                                        .build()
                                    val request = GetCredentialRequest.Builder()
                                        .addCredentialOption(googleIdOption)
                                        .build()
                                    val result = credentialManager.getCredential(context, request)
                                    val credential = result.credential
                                    if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                                        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                                        val gName = googleIdTokenCredential.displayName ?: ""
                                        val gEmail = googleIdTokenCredential.id
                                        onGoogleLogin(gName, gEmail)
                                        onDismiss()
                                    } else {
                                        showAccountPicker = true
                                    }
                                } catch (e: Exception) {
                                    showAccountPicker = true
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth().testTag("google_login_button"),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BentoCardBg,
                            contentColor = BentoDark
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BentoBorder)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text("G ", color = BentoPurple, fontWeight = FontWeight.Black, fontSize = 16.sp)
                            Text(
                                text = getProfileTranslation(selectedLangCode, "sign_in_google"),
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = BentoDark
                            )
                        }
                    }
                } else {
                    Button(
                        onClick = {
                            onSignOut()
                            onDismiss()
                        },
                        modifier = Modifier.fillMaxWidth().testTag("profile_logout_button"),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
                            contentColor = MaterialTheme.colorScheme.error
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.3f))
                    ) {
                        Text(
                            text = getProfileTranslation(selectedLangCode, "sign_out").uppercase(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            letterSpacing = 1.sp
                        )
                    }
                }
            }
        }
    }

    if (showAccountPicker) {
        Dialog(onDismissRequest = { showAccountPicker = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = BentoCardBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("G", fontWeight = FontWeight.Black, color = BentoPurple, fontSize = 24.sp)
                            Text("o", fontWeight = FontWeight.Black, color = Color(0xFFF87171), fontSize = 20.sp)
                            Text("o", fontWeight = FontWeight.Black, color = Color(0xFFFBBF24), fontSize = 20.sp)
                            Text("g", fontWeight = FontWeight.Black, color = BentoPurple, fontSize = 20.sp)
                            Text("l", fontWeight = FontWeight.Black, color = Color(0xFF34D399), fontSize = 20.sp)
                            Text("e", fontWeight = FontWeight.Black, color = Color(0xFFF87171), fontSize = 20.sp)
                        }
                        Text(
                            text = "✕",
                            modifier = Modifier.clickable { showAccountPicker = false },
                            fontWeight = FontWeight.Bold,
                            color = BentoMutedText
                        )
                    }

                    Text(
                        text = "Choose an account\nto continue to BMI Calculator",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = BentoDark
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        AccountItem(
                            email = "vinjax2001@gmail.com",
                            name = "Vinjax",
                            onClick = {
                                onGoogleLogin("Vinjax", "vinjax2001@gmail.com")
                                showAccountPicker = false
                                onDismiss()
                            }
                        )

                        AccountItem(
                            email = "guest.tracker@gmail.com",
                            name = "Healthy Guest",
                            onClick = {
                                onGoogleLogin("Healthy Guest", "guest.tracker@gmail.com")
                                showAccountPicker = false
                                onDismiss()
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
fun AccountItem(email: String, name: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("google_account_item_$name"),
        shape = RoundedCornerShape(12.dp),
        color = BentoGrayBg
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(BentoPurple, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name.trim().take(1).uppercase(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Column {
                Text(text = name, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = BentoDark)
                Text(text = email, fontSize = 11.sp, color = BentoMutedText)
            }
        }
    }
}
