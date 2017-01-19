import org.springframework.http.HttpInputMessage

class CustomGsonHttpMessageConverter extends RenameGsonHttpMessageConverter {
  override protected def renameReadInternal(clazz: Class[_], inputMessage: HttpInputMessage) = {
    // custom logic
    // or you may want to call
    superReadInternal(clazz, inputMessage)
  }
}
