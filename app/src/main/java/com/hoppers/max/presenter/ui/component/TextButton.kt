package com.hoppers.max.presenter.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle

@Composable
fun TextButton(modifier: Modifier = Modifier,text:String, onClick: (Int)-> Unit, style: TextStyle = TextStyle() ) {

    androidx.compose.foundation.text.ClickableText(
        text = AnnotatedString(text),
        onClick = onClick,
        style = style,
        modifier = modifier

    )

}