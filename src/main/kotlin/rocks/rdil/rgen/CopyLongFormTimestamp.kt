package rocks.rdil.rgen

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun copyToClipboard(text: String) {
    Toolkit.getDefaultToolkit().systemClipboard.setContents(StringSelection(text), null)
}

class CopyLongFormTimestamp : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        // equivalent of Time.now.utc.strftime("%Y%m%d%H%M%S")
        val utcNow = ZonedDateTime.now(ZoneOffset.UTC)
        val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
        val formattedDate = utcNow.format(formatter)

        copyToClipboard(formattedDate)

        Notification("long-form-timestamp", "Successfully copied!", NotificationType.INFORMATION)
            .notify(e.project)
    }
}
