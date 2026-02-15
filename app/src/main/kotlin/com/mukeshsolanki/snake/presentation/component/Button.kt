package com.mukeshsolanki.snake.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mukeshsolanki.snake.presentation.theme.DarkGreen
import com.mukeshsolanki.snake.presentation.theme.SnakeFontFamily
import com.mukeshsolanki.snake.presentation.theme.corner4dp
import com.mukeshsolanki.snake.presentation.theme.size64dp

@Composable
fun AppButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onBackground,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) { Text(text = text) }
}

@Composable
fun AppIconButton(modifier: Modifier = Modifier, icon: ImageVector, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(size64dp)
            .background(
                color = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(corner4dp)
            ),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}


/** BOTONES AÑADIDOS PARA EL MENÚ PRINCIPAL **/

@Composable
fun PixelButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {


    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState().value


    Box(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .background(Color.Black, RoundedCornerShape(8.dp))
            .padding(2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.Black, RoundedCornerShape(6.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .offset(y = if (isPressed) 4.dp else 0.dp)
                    .background(DarkGreen, RoundedCornerShape(4.dp))
                    .padding(top = 2.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkGreen, RoundedCornerShape(4.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = text.uppercase(),
                        color = Color.LightGray,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = SnakeFontFamily,
                        style = androidx.compose.ui.text.TextStyle(letterSpacing = 2.sp)
                    )
                }
            }
        }
    }
}

/** BOTONES AÑADIDOS PARA LA CRUCETA DIRECCIONAL **/
@Composable
fun PixelIconButton(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {


    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState().value


    Box(
        modifier = modifier
            .size(64.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .background(Color.Black, RoundedCornerShape(8.dp))
            .padding(2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.Black, RoundedCornerShape(6.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .offset(y = if (isPressed) 4.dp else 0.dp)
                    .background(DarkGreen, RoundedCornerShape(4.dp))
                    .padding(top = 2.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkGreen, RoundedCornerShape(4.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color.LightGray
                    )
                }
            }
        }
    }
}