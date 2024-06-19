package com.o5appstudio.saimakanwal.utils

import androidx.compose.runtime.remember
import com.o5appstudio.saimakanwal.R
import com.o5appstudio.saimakanwal.model.SliderItem
import com.o5appstudio.saimakanwal.model.SocialIcons

object Consts {

    const val SaimaKanwalApp = "SaimaKanwalApp"
    const val AllBooks = "AllBooks"
    const val AllCategories = "AllCategories"
    const val StatusPoetry = "StatusPoetry"
    const val Videos = "Videos"


    val socialIcons = listOf(
//        SocialIcons(R.drawable.ic_facebook, "https://www.facebook.com/saimakanwal78"),
        SocialIcons("Facebook",R.drawable.ic_facebook, "facebook"),
        SocialIcons("Youtube",R.drawable.ic_youtube, "https://www.youtube.com/@sukhantarazbykanwal"),
        SocialIcons("Tiktok",R.drawable.ic_tiktok, "https://www.tiktok.com/@saima.kanwal95"),
        SocialIcons("Instagram",R.drawable.ic_instagram, "https://www.instagram.com/poetsaimakanwal"),
        SocialIcons("Twitter",R.drawable.ic_twitter, "twitter"),
        SocialIcons("Whatsapp",R.drawable.ic_whatsapp, "https://api.whatsapp.com/send?phone=494915217114331"),
    )
    val optionsIcons = listOf(
        SocialIcons("Feedback", R.drawable.feedback_ic, "https://play.google.com/store/apps/details?id=com.o5appstudio.saimakanwal"),
        SocialIcons("Developer", R.drawable.developer_ic, "https://zainshakeelbutt.github.io/"),
        SocialIcons("Share App", R.drawable.share_ic, "https://play.google.com/store/apps/details?id=com.o5appstudio.saimakanwal"),
        SocialIcons("Privacy Policy", R.drawable.privacy, "https://sites.google.com/view/saimakanwalpoetryapp/home"),
        SocialIcons("Quit", R.drawable.power_off, "quit"),
    )


    val categoriesList = listOf(
        "اشعار",
        "افسانے",
        "حمد و نعت",
        "غزلیں",
        "قطعات",
        "مضامین",
        "نثر",
        "نظمیں"
    )

    val imagesDummyList = listOf(
        "https://firebasestorage.googleapis.com/v0/b/saima-kanwal-poetry.appspot.com/o/Poetry%20Status%2F1675603254067.null?alt=media&token=44d04b58-764e-4cf3-90c3-e038e6a587d1",
        "https://firebasestorage.googleapis.com/v0/b/saima-kanwal-poetry.appspot.com/o/Poetry%20Status%2F1680564811606.null?alt=media&token=eb9427b6-c00f-436d-8138-d682cb585fe3",
        "https://firebasestorage.googleapis.com/v0/b/saima-kanwal-poetry.appspot.com/o/Poetry%20Status%2F1680564913579.null?alt=media&token=91022f0a-3f05-4632-a768-3150776d2833",
        "https://firebasestorage.googleapis.com/v0/b/saima-kanwal-poetry.appspot.com/o/Poetry%20Status%2F1680565016818.null?alt=media&token=e35dc58b-8e3c-4b0b-86b5-3770c43bf33c"
    )

    val sliderItems =
        listOf(
            SliderItem(
                title = "تیرا یہ درد ہی ٹھہرا ہے میری کُل میراث\nتیرا یہ درد ہی اب میرے کام آئے گا",
                poet = "صائمہ کنولؔ"
            ),
            SliderItem(
                title = "آنسو نہیں ہیں شعر ہیں اب میری آنکھ میں\nتعبیر ہے کنولؔ یہ میرے ایک خواب کی",
                poet = "صائمہ کنولؔ"
            ),
            SliderItem(
                title = "بجھی آگ کو پھر ہوا دے گیا وہ\nمجھے عشق کی انتہا دے گیا وہ",
                poet = "صائمہ کنولؔ"
            )
        )



    const val introduction =
            "نام : صائمہ کنول\n" +
                    "قلمی نام/تخلص: کنول\n" +
                    "تاريخِ پیدائش: 4.4.1979\n" +
                    "\n" +
                    "رہائش :  پیدائش کراچی، دس سال لاہور \n" +
                    "اور 1991 سے جرمنی\n" +
                    " تعلیم:  ابتدائی سکول لاہور سلطان پورہ \n" +
                    "   گریجویٹ ڈپلوما ڈرمیٹو لوجی، \n" +
                    "  بیوٹیشن\n" +
                    "\n" +
                    "اساتذہ کے نام:    شہزاد احمد شاز ،\n" +
                    " شاذ آن لائن شعر و ادب اکیڈمی \n" +
                    "\n" +
                    "ادبی رفاقتیں \n" +
                    "۔ گلوبل رائٹر ایسوسیشن اٹلی، \n" +
                    "۔ حلقہ اربابِ ذوق جرمنی، \n" +
                    "۔ ہم شاعر و ادیب انٹرنیشنل ، \n" +
                    "۔ سوشل میڈیا پہ مختلف تنظیمیں \n" +
                    "\n" +
                    "پیشہ : بیوٹیشن \n" +
                    "\n" +
                    "پہلا شعر :\n" +
                    "  تمہاری ہی خواہش تھی حسرت ہماری \n" +
                    "  ہمیں دے گئی غم، یہ چاہت ہماری \n" +
                    "             \n" +
                    "اصناف سخن : نظم، غزلیات ، رباعی، مثنوی وغیرہ\n" +
                    "پسندیدہ اردو صنف:  غزل\n" +
                    "\n" +
                    "شاعری کا سفر: \n" +
                    " 2004  شاعری کی ابتداء ،\n" +
                    " 2008  نثر کی پہلی تحقیقی کتاب \"آبگینے\" \n" +
                    " 2020 آبگینے\" کا دوسرا ایڈیشن \"عورت\" \n" +
                    " 2020  پہلا مجموعہ کلام \" نئی منزل بلاتی ہے\" \n" +
                    " 2023  ڈیجیٹل کتاب پلے سٹور پہ ایپلیکیشن  \n" +
                    "Saima Kanwal poetry \n" +
                    " 2023 سخن طراز/ذوق سے شوق تک کس سفر\"\n" +
                    "فروغ ادب میں پہلا رموز شاعری کورس \n" +
                    " 2024 دوسرا شعری مجموعہ \"حسن خامشی\"\n" +
                    " 2024 پہلا گانا \"حسرت\" منظر عام پہ آیا\n" +
                    " 2024 دوسرا شعری مجموعہ \"حسن خامشی\"\n" +
                    " 2024سخن طراز/ذوق سے شوق تک کس سفر\"\n" +
                    "2024   فروغ ادب میں دوسرا رموز شاعری کورس \n" +
                    "2024  شاگردوں کے طرحی مشاعروں کا اہتمام \n" +
                    "\n" +
                    "ادبی تنظیموں سے وابستگی:\n" +
                    " ۔  گلوبل رائٹر ایسوسیشن اٹلی، \n" +
                    " ۔ حلقہ اربابِ ذوق جرمنی\n" +
                    "۔ سوشل میڈیا کی مختلف تنظیمیں\n" +
                    "\n" +
                    "تمغاجات/اعزازات:\n" +
                    "۔ \"اعزازی شیلڈ\"   حلقہ ارباب ذوق جرمنی  \n" +
                    "۔ \"ٹیلنٹڈ ایوارڈ\"   گلوبل رائٹر ایسوسیشن اٹلی\n" +
                    "۔ \"ادبی خدمات ایوارڈ\" بلھےشاہ گولڈ میڈل\n" +
                    "۔ \"سند امتیاز \"، \"سند سپاس\"، \"سند توصیفی\"\n" +
                    "گلوبل رائٹر ایسوسی سیشن اٹلی \n" +
                    "\n" +
                    "پسندیدہ لکھاری/شاعر\n" +
                    "فیض احمد فیض، شعیب کیانی، پروین شاکر\n" +
                    "\n" +
                    "زیر طبع/آمدہ تصانیف:\n" +
                    "۔ افسانوی مجموعہ\n" +
                    "۔ گانا \" گرد\" جلد آن ائیر \n" +
                    "۔ رموز شاعری کا تیسرا مرحلہ\n"
    }




