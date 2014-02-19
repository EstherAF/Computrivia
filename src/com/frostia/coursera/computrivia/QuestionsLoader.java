package com.frostia.coursera.computrivia;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

public class QuestionsLoader {
	
	private static final String TAG = "QuestionsLoader";
	
	private static final String QUESTION_CODE = "_q";
	private static final String ANSWER_CODE = "_a";
	
	private static final int FIRST_QUESTION_NUMBER=1;
	private int actualQuestion;
	private int maxQuestion; 
	
	private Map<Category, Integer> questionViews;
	private Map<Category, Integer> answerViews;

	private Map<Category, List<AnswerQuestionPair>> questionsAnswersBundle;
	
	private Boolean isLoaded;
	
	public QuestionsLoader() {
		this.questionViews= new LinkedHashMap<Category, Integer>();
		this.answerViews= new LinkedHashMap<Category, Integer>();
		this.questionsAnswersBundle= new LinkedHashMap<Category, List<AnswerQuestionPair>>();
		this.actualQuestion=0;
		this.maxQuestion=0;
		this.isLoaded=false;
	}

	public void loadAnswersQuestionsBundle(Activity activity) {
		if(questionViews.isEmpty() || answerViews.isEmpty()){
			return;
		}
		
		for(Category cat: questionViews.keySet()){
			Log.d(TAG, "Loading category "+cat.toString());
		
			int numberOfQuestion=FIRST_QUESTION_NUMBER;
			
			List<AnswerQuestionPair> pairs = new ArrayList<AnswerQuestionPair>();
			this.questionsAnswersBundle.put(cat, pairs);
			
			String question, answer;
			do{
				String propertyQuestionName = 
						cat.toString().toLowerCase(Locale.US) + QUESTION_CODE + numberOfQuestion;
				String propertyAnswerName = 
						cat.toString().toLowerCase(Locale.US) + ANSWER_CODE + numberOfQuestion;
				
				question = getStringResourceByName(activity, propertyQuestionName);
				answer = getStringResourceByName(activity, propertyAnswerName);
				
				if(question.length()>0 && answer.length()>0){
					AnswerQuestionPair pair = new AnswerQuestionPair(question, answer, cat);
					Log.d(TAG, "Adding pair "+pair);
					pairs.add(pair);
				}
				
				if(this.maxQuestion!=0 && numberOfQuestion>this.maxQuestion)
					break;
				else
					numberOfQuestion++;
				
			}while(question.length()>0 && answer.length()>0);
			
			int lastPosition = pairs.size()-1;
			
			if(this.maxQuestion==0 || lastPosition<this.maxQuestion){
				this.maxQuestion=lastPosition;
				Log.d(TAG, "Max number of questions CHANGED:"+this.maxQuestion);
			}
		}
		
		this.isLoaded=true;
	}

	public void showNextQuestion(Activity a) {
		this.actualQuestion = (this.actualQuestion==this.maxQuestion)? 0 : this.actualQuestion+1;
		
		Log.d(TAG,"Showing next question "+this.actualQuestion);
		
		for(Category cat : Category.values()){
			Log.d(TAG,"Category "+cat);
			
			AnswerQuestionPair pair = this.questionsAnswersBundle.get(cat).get(this.actualQuestion);
			Log.d(TAG, "Pair "+pair);
			
			Integer id = questionViews.get(cat);
			((TextView) a.findViewById(id)).setText(pair.getQuestion());
		}
		
	}
	
	public void showActualQuestion(Activity a) {
		Log.d(TAG,"Showing actual question "+this.actualQuestion);
		
		for(Category cat : Category.values()){
			Log.d(TAG,"Category "+cat);
			
			AnswerQuestionPair pair = this.questionsAnswersBundle.get(cat).get(this.actualQuestion);
			Log.d(TAG, "Pair "+pair);
			
			Integer id = questionViews.get(cat);
			((TextView) a.findViewById(id)).setText(pair.getQuestion());
		}
	}
	
	public void showActualAnswer(Activity a) {
		Log.d(TAG,"Showing actual answer "+this.actualQuestion);
		
		for(Category cat : Category.values()){
			Log.d(TAG,"Category "+cat);
			
			AnswerQuestionPair pair = this.questionsAnswersBundle.get(cat).get(this.actualQuestion);
			Log.d(TAG, "Pair "+pair);
			
			Integer id = answerViews.get(cat);
			((TextView) a.findViewById(id)).setText(pair.getAnswer());
		}
		
	}
	
	private String getStringResourceByName(Activity activity, String aString) {
		Log.d(TAG,"Getting string resource '"+aString+"'");
		
	    String packageName = activity.getPackageName();
	    int resId = activity.getResources()
	            .getIdentifier(aString, "string", packageName);
	    
	    String getted = (resId == 0)? "" : activity.getString(resId);
	    Log.d(TAG, "Getted "+getted);
	    return getted;
	}
	
	public void addQuestionView(Category category, Integer view) {
		Log.d(TAG, "Adding question view:"+category+"|"+view);
		this.questionViews.put(category, view);
	}

	public void addAnswerView(Category category, Integer view) {
		Log.d(TAG, "Adding answer view:"+category+"|"+view);
		this.answerViews.put(category, view);
	}

	public Boolean getIsLoaded() {
		return isLoaded;
	}

	

}
