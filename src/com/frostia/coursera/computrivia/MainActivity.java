package com.frostia.coursera.computrivia;

import com.frostia.coursera.computrivia.R;
import com.frostia.coursera.computrivia.Category;
import com.frostia.coursera.computrivia.QuestionsLoader;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private QuestionsLoader questionsLoader = new QuestionsLoader();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		createQuestionLoader();
		
		int orientation = getResources().getConfiguration().orientation;
		Log.d(TAG,"ORIENTATION CHANGED:"+orientation);
		
		if(orientation==1){
			this.questionsLoader.showActualQuestion(this);
		}else{
			this.questionsLoader.showActualAnswer(this);
		}
		
		final View nextQuestion = findViewById(R.id.next_button);
		final Activity self=this;
		
		if(nextQuestion!=null){
			nextQuestion.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					questionsLoader.showNextQuestion(self);
				}
			});
		}
	}

	
	private void createQuestionLoader(){
		if(this.questionsLoader.getIsLoaded()){
			//Already created
			return;
		}
		
		//Get views that will be changed by questionLoader
		questionsLoader.addQuestionView(Category.HW, R.id.hw_question);
		questionsLoader.addQuestionView(Category.SYS, R.id.sys_question);
		questionsLoader.addQuestionView(Category.DEV, R.id.dev_question);
		questionsLoader.addQuestionView(Category.HIS, R.id.his_question);
		questionsLoader.addQuestionView(Category.MISC, R.id.mis_question);
		
		questionsLoader.addAnswerView(Category.HW, R.id.hw_answer);
		questionsLoader.addAnswerView(Category.SYS, R.id.sys_answer);
		questionsLoader.addAnswerView(Category.DEV, R.id.hw_answer);
		questionsLoader.addAnswerView(Category.HIS, R.id.his_answer);
		questionsLoader.addAnswerView(Category.MISC, R.id.mis_answer);
		
		//Load content from XML
		this.questionsLoader.loadAnswersQuestionsBundle(this);
	}

}
