package program.translator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class TranslateResponse {
    @SerializedName("text")
    @Expose
    List<String> text;
}
