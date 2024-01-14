package app.ui.theme.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Filtericon: ImageVector
    get() {
        if (_filtericon != null) {
            return _filtericon!!
        }
        _filtericon = Builder(
            name = "Filtericon", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
            viewportWidth = 960.0f, viewportHeight = 960.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(400.0f, 720.0f)
                verticalLineToRelative(-80.0f)
                horizontalLineToRelative(160.0f)
                verticalLineToRelative(80.0f)
                lineTo(400.0f, 720.0f)
                close()
                moveTo(240.0f, 520.0f)
                verticalLineToRelative(-80.0f)
                horizontalLineToRelative(480.0f)
                verticalLineToRelative(80.0f)
                lineTo(240.0f, 520.0f)
                close()
                moveTo(120.0f, 320.0f)
                verticalLineToRelative(-80.0f)
                horizontalLineToRelative(720.0f)
                verticalLineToRelative(80.0f)
                lineTo(120.0f, 320.0f)
                close()
            }
        }
            .build()
        return _filtericon!!
    }

private var _filtericon: ImageVector? = null
