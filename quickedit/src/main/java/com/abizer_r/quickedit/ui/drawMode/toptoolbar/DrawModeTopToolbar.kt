package com.abizer_r.quickedit.ui.drawMode.toptoolbar

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Redo
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.abizer_r.quickedit.theme.QuickEditTheme
import com.abizer_r.quickedit.theme.ToolBarBackgroundColor
import com.abizer_r.quickedit.ui.editorScreen.bottomToolbar.TOOLBAR_HEIGHT_SMALL

@Composable
fun DrawModeTopToolBar(
    modifier: Modifier,
    undoEnabled: Boolean = false,
    redoEnabled: Boolean = false,
    showCloseAndDone: Boolean = true,
    closeEnabled: Boolean = true,
    doneEnabled: Boolean = false,
    toolbarHeight: Dp = TOOLBAR_HEIGHT_SMALL,
    onUndo: () -> Unit,
    onRedo: () -> Unit,
    onCloseClicked: () -> Unit = {},
    onDoneClicked: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .height(toolbarHeight)
            .background(ToolBarBackgroundColor),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        /**
         * Tool Item: CLOSE
         */
        if (showCloseAndDone) {
            Image(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(32.dp)
                    .clickable {
                        onCloseClicked()
                    },
                contentDescription = null,
                imageVector = Icons.Default.Close,
                colorFilter = ColorFilter.tint(
                    color = if (closeEnabled) {
                        MaterialTheme.colorScheme.onBackground
                    } else Color.DarkGray
                )
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        /**
         * Tool Item: UNDO
         */
        Image(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(32.dp)
                .clickable {
                    onUndo()
                },
            contentDescription = null,
            imageVector = Icons.Default.Undo,
            colorFilter = ColorFilter.tint(
                color = if (undoEnabled) {
                    MaterialTheme.colorScheme.onBackground
                } else Color.DarkGray
            )
        )

        /**
         * Tool Item: REDO
         */
        Image(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(32.dp)
                .clickable {
                    onRedo()
                },
            contentDescription = null,
            imageVector = Icons.Default.Redo,
            colorFilter = ColorFilter.tint(
                color = if (redoEnabled) {
                    MaterialTheme.colorScheme.onBackground
                } else Color.DarkGray
            )
        )


        Spacer(modifier = Modifier.weight(1f))


        /**
         * Tool Item: DONE
         */
        if (showCloseAndDone) {
            Image(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(32.dp)
                    .clickable {
                        onDoneClicked()
                    },
                contentDescription = null,
                imageVector = Icons.Default.Check,
                colorFilter = ColorFilter.tint(
                    color = if (doneEnabled) {
                        MaterialTheme.colorScheme.onBackground
                    } else Color.DarkGray
                )
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewTopToolbar() {
    QuickEditTheme {
        DrawModeTopToolBar(
            modifier = Modifier.fillMaxWidth(),
            undoEnabled = true,
            onUndo = {},
            onRedo = {},
            onCloseClicked = {},
            onDoneClicked = {}
        )
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewTopToolbar2() {
    QuickEditTheme {
        DrawModeTopToolBar(
            modifier = Modifier.fillMaxWidth(),
            undoEnabled = true,
            showCloseAndDone = false,
            onUndo = {},
            onRedo = {},
            onCloseClicked = {},
            onDoneClicked = {}
        )
    }
}