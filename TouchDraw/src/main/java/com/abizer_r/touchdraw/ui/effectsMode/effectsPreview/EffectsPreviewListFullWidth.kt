package com.abizer_r.touchdraw.ui.effectsMode.effectsPreview

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abizer_r.components.R
import com.abizer_r.components.theme.Black_alpha_30
import com.abizer_r.components.theme.SketchDraftTheme
import com.abizer_r.components.theme.ToolBarBackgroundColor
import com.abizer_r.components.util.ImmutableList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EffectsPreviewListFullWidth(
    modifier: Modifier = Modifier,
    effectsList: ArrayList<EffectItem>,
    selectedIndex: Int,
    onItemClicked: (position: Int, effectItem: EffectItem) -> Unit
) {

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(
            count = effectsList.size,
            key = { it },
        ) {index ->
            val effectItem = effectsList[index]
            EffectPreview(
                modifier = Modifier
                    .animateItemPlacement()
                    .padding(horizontal = 4.dp),
                effectItem = effectItem,
                isSelected = index == selectedIndex,
                selectedBorderColor = MaterialTheme.colorScheme.onBackground,
                onClick = {
                    onItemClicked(index, it)
                }
            )
        }
    }
}

@Composable
fun EffectPreview(
    modifier: Modifier = Modifier,
    effectItem: EffectItem,
    isSelected: Boolean,
    itemSize: Dp = 84.dp,
    selectedBorderWidth: Dp = 1.dp,
    selectedBorderColor: Color = Color.White,
    clipShape: Shape = RectangleShape,
    onClick: (effectItem: EffectItem) -> Unit
) {

    val borderColor = if (isSelected) selectedBorderColor else Color.Transparent
    Box(
        modifier = modifier
            .clip(clipShape)
            .background(color = borderColor)
            .padding(selectedBorderWidth)
            .clip(clipShape)
            .size(itemSize)
            .clickable {
                onClick(effectItem)
            }
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            bitmap = effectItem.previewBitmap.asImageBitmap(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier
                .background(Black_alpha_30)
                .align(Alignment.BottomCenter),
            text = effectItem.label,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 9.sp,
            ),
        )
    }

}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun Selected_EffectPreviewItem() {
    SketchDraftTheme {
        val bitmap = ImageBitmap.imageResource(id = R.drawable.placeholder_image_1).asAndroidBitmap()
        EffectPreview(
            modifier = Modifier.padding(8.dp),
            effectItem = EffectItem(
                ogBitmap = bitmap,
                previewBitmap = bitmap,
                label = "Dummy Effect"
            ),
            isSelected = true,
            onClick = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun Unselected_EffectPreviewItem() {
    SketchDraftTheme {
        val bitmap = ImageBitmap.imageResource(id = R.drawable.placeholder_image_1).asAndroidBitmap()
        EffectPreview(
            modifier = Modifier.padding(8.dp),
            effectItem = EffectItem(
                ogBitmap = bitmap,
                previewBitmap = bitmap,
                label = "Dummy Effect"
            ),
            isSelected = false,
            onClick = {}
        )
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun Preview_EffectsPreviewList() {
    val bitmap = ImageBitmap.imageResource(id = R.drawable.placeholder_image_1).asAndroidBitmap()
    val mEffectsList = arrayListOf(
        EffectItem(
            ogBitmap = bitmap,
            previewBitmap = bitmap,
            label = "original"
        ),
        EffectItem(
            ogBitmap = bitmap,
            previewBitmap = bitmap,
            label = "greyscale"
        ),
        EffectItem(
            ogBitmap = bitmap,
            previewBitmap = bitmap,
            label = "poppy dogs"
        )
    )
    SketchDraftTheme {
        EffectsPreviewListFullWidth(
            modifier = Modifier
                .background(ToolBarBackgroundColor)
                .padding(vertical = 12.dp),
            effectsList = mEffectsList,
            selectedIndex = 0,
            onItemClicked = {_, _ ->}
        )
    }
}