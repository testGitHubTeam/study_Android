package kr.android.flipper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements OnClickListener{
	
	ViewFlipper myViewFlipper;
	Button btnPrev, btnNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myViewFlipper = (ViewFlipper)findViewById(R.id.vfDetails);
		btnPrev = (Button)findViewById(R.id.btnPrev);
		btnNext =(Button)findViewById(R.id.btnNext);
		
		btnPrev.setOnClickListener(this);
		btnNext.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btnNext){	//¥Ÿ¿Ω ∫‰ »£√‚
			myViewFlipper.showNext();			
		}else if(v.getId()==R.id.btnPrev){	//¿Ã¿¸ ∫‰ »£√‚
			myViewFlipper.showPrevious();					
		}		
	}
}