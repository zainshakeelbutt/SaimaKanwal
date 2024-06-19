package com.o5appstudio.saimakanwal.screens.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.o5appstudio.saimakanwal.R
import com.o5appstudio.saimakanwal.ui.theme.background
import com.o5appstudio.saimakanwal.ui.theme.containerColor

@Composable
fun PoetryListItem(title:String, detail:String, onClick: ()-> Unit) {
    Box (
        modifier = Modifier

            .shadow(elevation = 2.dp, clip = true, shape = RoundedCornerShape(12))
            .clip(shape = RoundedCornerShape(12))
            .background(color = containerColor, shape = RoundedCornerShape(12))
            .fillMaxWidth()
            .clickable { onClick() }

            .padding(20.dp)

    ){

        Column {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    textDirection = TextDirection.Rtl,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.noto_font)),
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text =detail,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    textDirection = TextDirection.Rtl,
                    color = Color.Gray,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.noto_font)),
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}