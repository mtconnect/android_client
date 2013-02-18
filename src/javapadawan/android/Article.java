package javapadawan.android;
import android.app.Application;
import android.content.Intent;

public class Article extends Application {
	private String someVariable;
	
	public String getSomeVariable() {
		return someVariable;
	}
    public static final Intent createYourSpecialIntent(Intent src) {
    	return new Intent("YourSpecialIntent").addCategory("YourSpecialCategory").putExtras(src);
    	
    }
	public void setSomeVariable(String someVariable) {
		this.someVariable = someVariable;
	}
}

