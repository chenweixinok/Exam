package exam.xiaolian;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String DB_PATH="/data/data/exam.xiaolian/databases/";
		String DB_NAME="question.db";
		if ((new File(DB_PATH+DB_NAME).exists())==false) {
			File dir=new File(DB_PATH);
			if (!dir.exists()) {
				dir.mkdir();
			}
			
			try {
				InputStream is=getBaseContext().getAssets().open(DB_NAME);
				OutputStream os=new FileOutputStream(DB_PATH+DB_NAME);
				byte[] buffer=new byte[1024];
				int length;
				
				while ((length=is.read(buffer))>0) {
					os.write(buffer,0,length);
				}
				os.flush();
				os.close();
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Button btn=(Button) findViewById(R.id.exam_btn);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,ExamActivity.class);
				startActivity(intent);
			}
		});
	}

}
