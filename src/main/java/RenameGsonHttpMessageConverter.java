import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.io.IOException;

public abstract class RenameGsonHttpMessageConverter extends GsonHttpMessageConverter {

    protected Object superReadInternal(Class<?> clazz, HttpInputMessage inputMessage) throws
            IOException, HttpMessageNotReadableException {
        return super.readInternal(clazz, inputMessage);
    }

    abstract protected Object renameReadInternal(Class<?> clazz, HttpInputMessage inputMessage) throws
            IOException, HttpMessageNotReadableException;

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return renameReadInternal(clazz, inputMessage);
    }
}
