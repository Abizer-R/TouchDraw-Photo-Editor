package com.abizer_r.touchdraw.ui.drawingCanvas

import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import com.abizer_r.touchdraw.ui.drawingCanvas.drawingTool.shapes.AbstractShape
import com.abizer_r.touchdraw.ui.drawingCanvas.models.PathDetails
import com.abizer_r.touchdraw.ui.editorScreen.bottomToolbar.state.BottomToolbarItem
import com.abizer_r.touchdraw.utils.getShape
import java.util.Stack

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DrawingCanvas(
    modifier: Modifier = Modifier,
    pathDetailStack: Stack<PathDetails>,
    selectedColor: Color,
    currentTool: BottomToolbarItem,
    onDrawingEvent: (DrawingEvents) -> Unit
) {
//    Log.e("TEST", "DrawingCanvas: drawingTool = ${drawingState.drawingTool}", )

    /**
     * The variables/states below are changed inside the ".pointerInteropFilter" modifier
     * And when these are changed, the draw phase is called (compose has 3 phases: composition, layout and draw)
     * SO, Recomposition isn't triggered
     */
    var currentShape: AbstractShape? = null
    var drawPathAction by remember { mutableStateOf<Any?>(null) }

    Canvas(
        modifier = modifier
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        currentShape = currentTool.getShape(selectedColor = selectedColor)
                        currentShape?.initShape(startX = it.x, startY = it.y)
                    }

                    MotionEvent.ACTION_MOVE -> {
                        currentShape?.moveShape(endX = it.x, endY = it.y)


                        // Below state is just to trigger the draw() phase of canvas
                        // Without it, current path won't be drawn until the "drawState" is changed
                        drawPathAction = Offset(it.x, it.y)
                    }

                    MotionEvent.ACTION_CANCEL,
                    MotionEvent.ACTION_UP -> {
                        if (currentShape != null && currentShape!!.shouldDraw()) {
                            onDrawingEvent(
                                DrawingEvents.AddNewPath(
                                    pathDetail = PathDetails(
                                        drawingShape = currentShape!!,
                                    )
                                )
                            )
                        }
                    }
                }
                true
            }


    ) {
        pathDetailStack.forEach { pathDetails ->
            Log.e("TEST", "DrawingCanvas: drawing from stack. drawingShape = ${pathDetails.drawingShape}", )
            pathDetails.drawingShape.draw(
                drawScope = this,
            )
        }

        Log.e("TEST", "DrawingCanvas: done \n\n\n", )

        drawPathAction?.let {
            currentShape?.draw(
                drawScope = this,
            )
        }
    }
}