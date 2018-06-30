package com.example.android.quizapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    final int NUMBER_OF_QUESTIONS = 8;
    final int NUMBER_OF_STARS = 5;

    int answeredQuestions= 0;
    boolean finished = false;

    RatingBar ratingBar;
    TextView answeredQuestionsTextView;

    TextView q1Mark, q2Mark, q3Mark, q4Mark, q5Mark, q6Mark, q7Mark, q8Mark;

    EditText q1EditText;
    RadioGroup q2OptionGroup;
    RadioButton q2OptionA, q2OptionB, q2OptionC, q2OptionD;
    EditText q3EditText;
    CheckBox q4OptionA, q4OptionB, q4OptionC, q4OptionD;
    RadioGroup q5OptionGroup;
    RadioButton q5OptionA, q5OptionB, q5OptionC, q5OptionD;
    RadioGroup q6OptionGroup;
    RadioButton q6OptionA, q6OptionB;
    RadioGroup q7OptionGroup;
    RadioButton q7OptionA, q7OptionB, q7OptionC, q7OptionD;
    CheckBox q8OptionA, q8OptionB, q8OptionC, q8OptionD;

    Button actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialise and get all use view group references
        initialise();

        // reset all view groups and variables to their default values
        reset();
    }

    @Override
    public void onBackPressed()
    {
        // on hardware back button pressed, quit the app
        quitApp();
    }

    /**
     * Retrieve all important view groups and set their appropriate listeners
     */
    void initialise()
    {
        ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        ratingBar.setStepSize(1/(float)NUMBER_OF_STARS);
        setRatings(calculateBestRating());

        answeredQuestionsTextView = (TextView) findViewById(R.id.answered_ratio_text_view);

        q1Mark = (TextView) findViewById(R.id.q1_mark_text_view);
        q2Mark = (TextView) findViewById(R.id.q2_mark_text_view);
        q3Mark = (TextView) findViewById(R.id.q3_mark_text_view);
        q4Mark = (TextView) findViewById(R.id.q4_mark_text_view);
        q5Mark = (TextView) findViewById(R.id.q5_mark_text_view);
        q6Mark = (TextView) findViewById(R.id.q6_mark_text_view);
        q7Mark = (TextView) findViewById(R.id.q7_mark_text_view);
        q8Mark = (TextView) findViewById(R.id.q8_mark_text_view);

        q1EditText = (EditText) findViewById(R.id.q1_answer_edit_text);
        q1EditText.addTextChangedListener(textChangedListener);

        q2OptionGroup = (RadioGroup) findViewById(R.id.q2_options_radio_group);
        q2OptionGroup.setOnCheckedChangeListener(radioButtonCheckedListener);

        q2OptionA = (RadioButton) findViewById(R.id.q2_optionA_radio_button);
        q2OptionB = (RadioButton) findViewById(R.id.q2_optionB_radio_button);
        q2OptionC = (RadioButton) findViewById(R.id.q2_optionC_radio_button);
        q2OptionD = (RadioButton) findViewById(R.id.q2_optionD_radio_button);

        q3EditText = (EditText) findViewById(R.id.q3_answer_edit_text);
        q3EditText.addTextChangedListener(textChangedListener);

        q4OptionA = (CheckBox) findViewById(R.id.q4_optionA_check_box);
        q4OptionA.setOnCheckedChangeListener(checkBoxOnCheckedListener);

        q4OptionB = (CheckBox) findViewById(R.id.q4_optionB_check_box);
        q4OptionB.setOnCheckedChangeListener(checkBoxOnCheckedListener);

        q4OptionC = (CheckBox) findViewById(R.id.q4_optionC_check_box);
        q4OptionC.setOnCheckedChangeListener(checkBoxOnCheckedListener);

        q4OptionD = (CheckBox) findViewById(R.id.q4_optionD_check_box);
        q4OptionD.setOnCheckedChangeListener(checkBoxOnCheckedListener);

        q5OptionGroup = (RadioGroup) findViewById(R.id.q5_options_radio_group);
        q5OptionGroup.setOnCheckedChangeListener(radioButtonCheckedListener);

        q5OptionA = (RadioButton) findViewById(R.id.q5_optionA_radio_button);
        q5OptionB = (RadioButton) findViewById(R.id.q5_optionB_radio_button);
        q5OptionC = (RadioButton) findViewById(R.id.q5_optionC_radio_button);
        q5OptionD = (RadioButton) findViewById(R.id.q5_optionD_radio_button);

        q6OptionGroup = (RadioGroup) findViewById(R.id.q6_options_radio_group);
        q6OptionGroup.setOnCheckedChangeListener(radioButtonCheckedListener);

        q6OptionA = (RadioButton) findViewById(R.id.q6_optionA_radio_button);
        q6OptionB = (RadioButton) findViewById(R.id.q6_optionB_radio_button);

        q7OptionGroup = (RadioGroup) findViewById(R.id.q7_options_radio_group);
        q7OptionGroup.setOnCheckedChangeListener(radioButtonCheckedListener);

        q7OptionA = (RadioButton) findViewById(R.id.q7_optionA_radio_button);
        q7OptionB = (RadioButton) findViewById(R.id.q7_optionB_radio_button);
        q7OptionC = (RadioButton) findViewById(R.id.q7_optionC_radio_button);
        q7OptionD = (RadioButton) findViewById(R.id.q7_optionD_radio_button);

        q8OptionA = (CheckBox) findViewById(R.id.q8_optionA_check_box);
        q8OptionA.setOnCheckedChangeListener(checkBoxOnCheckedListener);

        q8OptionB = (CheckBox) findViewById(R.id.q8_optionB_check_box);
        q8OptionB.setOnCheckedChangeListener(checkBoxOnCheckedListener);

        q8OptionC = (CheckBox) findViewById(R.id.q8_optionC_check_box);
        q8OptionC.setOnCheckedChangeListener(checkBoxOnCheckedListener);

        q8OptionD = (CheckBox) findViewById(R.id.q8_optionD_check_box);
        q8OptionD.setOnCheckedChangeListener(checkBoxOnCheckedListener);

        actionButton = (Button) findViewById(R.id.action_button);
    }

    /**
     * Set the star ratings and update the view group
     * @param ratings stars based on the best score
     */
    void setRatings(float ratings)
    {
        ratingBar.setRating(ratings);
    }

    /**
     * Set the number of answered questions and update the view group
     * @param num is the number of questions that has been answered
     */
    void setAnsweredQuestions(int num)
    {
        answeredQuestions = num;
        answeredQuestionsTextView.setText(getString(R.string.answered_progress_ratio, answeredQuestions, NUMBER_OF_QUESTIONS));
    }

    /**
     * Sets the action button text based on the finished state of the quiz
     */
    void resetButton()
    {
        if (finished)
        {
            actionButton.setText(getString(R.string.reset));
        }
        else
        {
            actionButton.setText(getString(R.string.submit));
        }
    }

    /**
     * Set the default value of the edit text answer view group in question 1
     */
    void resetQuestion1()
    {
        q1EditText.setText("");
    }

    /**
     * Set the default values of the radio button option view groups in question 2
     */
    void resetQuestion2()
    {
        q2OptionGroup.clearCheck();

        q2OptionA.setChecked(false);
        q2OptionB.setChecked(false);
        q2OptionC.setChecked(false);
        q2OptionD.setChecked(false);
    }

    /**
     * Set the default value of the edit text answer view group in question 3
     */
    void resetQuestion3()
    {
        q3EditText.setText("");
    }

    /**
     * Set the default values of the check box option view groups in question 4
     */
    void resetQuestion4()
    {
        q4OptionA.setChecked(false);
        q4OptionB.setChecked(false);
        q4OptionC.setChecked(false);
        q4OptionD.setChecked(false);
    }

    /**
     * Set the default values of the radio button option view groups in question 5
     */
    void resetQuestion5()
    {
        q5OptionGroup.clearCheck();

        q5OptionA.setChecked(false);
        q5OptionB.setChecked(false);
        q5OptionC.setChecked(false);
        q5OptionD.setChecked(false);
    }

    /**
     * Set the default values of the radio button option view groups in question 6
     */
    void resetQuestion6()
    {
        q6OptionGroup.clearCheck();

        q6OptionA.setChecked(false);
        q6OptionB.setChecked(false);
    }

    /**
     * Set the default values of the radio button option view groups in question 7
     */
    void resetQuestion7()
    {
        q7OptionGroup.clearCheck();

        q7OptionA.setChecked(false);
        q7OptionB.setChecked(false);
        q7OptionC.setChecked(false);
        q7OptionD.setChecked(false);
    }

    /**
     * Set the default values of the check box option view groups in question 8
     */
    void resetQuestion8()
    {
        q8OptionA.setChecked(false);
        q8OptionB.setChecked(false);
        q8OptionC.setChecked(false);
        q8OptionD.setChecked(false);
    }

    /**
     * Verify the correct answer inputted in question 1
     * @return 1 if correct and 0 if wrong
     */
    int checkQuestion1Answer()
    {
        String answer = q1EditText.getText().toString();

        if (answer.equals(getString(R.string.year_2011)))
        {
            setMark(q1Mark, true);
            return 1;
        }

        setMark(q1Mark, false);
        return 0;
    }

    /**
     * Verify the correct answer is checked in question 2
     * @return 1 if correct and 0 if wrong
     */
    int checkQuestion2Answer()
    {
        int selectedId = q2OptionGroup.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        RadioButton radioAnswerButton = (RadioButton) findViewById(selectedId);
        String answer = radioAnswerButton.getText().toString();

        if (answer.equals(getString(R.string.dolly_parton)))
        {
            setMark(q2Mark, true);
            return 1;
        }

        setMark(q2Mark, false);
        return 0;
    }

    /**
     * Verify that correct answer inputted in question 3
     * @return 1 if correct and 0 if wrong
     */
    int checkQuestion3Answer()
    {
        String answer = q3EditText.getText().toString();

        if (answer.equalsIgnoreCase(getString(R.string.band)) || answer.equalsIgnoreCase(getString(R.string.group)))
        {
            setMark(q3Mark, true);
            return 1;
        }

        setMark(q3Mark, false);
        return 0;
    }

    /**
     * Verify the correct answers are checked in question 4
     * @return 1 if correct and 0 if wrong
     */
    int checkQuestion4Answer()
    {
        boolean littleBigTown = q4OptionA.isChecked();
        boolean lukeBryan = q4OptionB.isChecked();
        boolean floridaGeorgiaLine = q4OptionC.isChecked();
        boolean ladyAntebellum = q4OptionD.isChecked();

        if (littleBigTown && lukeBryan && ladyAntebellum && !floridaGeorgiaLine)
        {
            setMark(q4Mark, true);
            return 1;
        }

        setMark(q4Mark, false);
        return 0;
    }

    /**
     * Verify the correct answer is checked in question 5
     * @return 1 if correct and 0 if wrong
     */
    public int checkQuestion5Answer()
    {
        int selectedId = q5OptionGroup.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        RadioButton radioAnswerButton = (RadioButton) findViewById(selectedId);
        String answer = radioAnswerButton.getText().toString();

        if (answer.equals(getString(R.string.luke_bryan)))
        {
            setMark(q5Mark, true);
            return 1;
        }

        setMark(q5Mark, false);
        return 0;
    }

    /**
     * Verify the correct answer is checked in question 6
     * @return 1 if correct and 0 if wrong
     */
    public int checkQuestion6Answer()
    {
        int selectedId = q6OptionGroup.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        RadioButton radioAnswerButton = (RadioButton) findViewById(selectedId);
        String answer = radioAnswerButton.getText().toString();

        if (answer.equals(getString(R.string.true_)))
        {
            setMark(q6Mark, true);
            return 1;
        }

        setMark(q6Mark, false);
        return 0;
    }

    /**
     * Verify the correct answer is checked in question 7
     * @return 1 if correct and 0 if wrong
     */
    public int checkQuestion7Answer()
    {
        RadioGroup optionGroup = (RadioGroup) findViewById(R.id.q7_options_radio_group);
        int selectedId = optionGroup.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        RadioButton radioAnswerButton = (RadioButton) findViewById(selectedId);
        String answer = radioAnswerButton.getText().toString();

        if(answer.equals(getString(R.string.lady_antebellum)))
        {
            setMark(q7Mark, true);
            return 1;
        }

        setMark(q7Mark, false);
        return 0;
    }

    /**
     * Verify the correct answers are checked in question 8
     * @return 1 if correct and 0 if wrong
     */
    public int checkQuestion8Answer()
    {
        boolean dixieChicks = q8OptionA.isChecked();
        boolean chrisStapleton = q8OptionB.isChecked();
        boolean rogerMiller = q8OptionC.isChecked();
        boolean ladyAntebellum = q8OptionD.isChecked();

        if (!dixieChicks && chrisStapleton && ladyAntebellum && rogerMiller)
        {
            setMark(q8Mark, true);
            return 1;
        }

        setMark(q8Mark, false);
        return 0;
    }

    /**
     * Modifies the text and text colour values of the Text View
     * @param textView Text View reference to be modified
     * @param correct variable that determines what nature of the modification
     */
    void setMark(TextView textView, boolean correct)
    {
        if (correct)
        {
            // set the text to be "Correct"
            textView.setText(getString(R.string.correct));
            // set the text colour to green
            textView.setTextColor(0xFF4CAF50);
        }
        else
        {
            // set the text to be "Wrong"
            textView.setText(getString(R.string.wrong));
            // set the text colour to be red
            textView.setTextColor(0xFFF44336);
        }
    }

    /**
     * This method is called when the action button is clicked.
     * @param view view of the activity
     */
    public void onActionButtonClicked(View view) {
        if (finished)
        {
            reset();
        }
        else
        {
            if (isAnyQuestionUnanswered())
            {
                Toast.makeText(getApplicationContext(), "Please answer all the questions available", Toast.LENGTH_SHORT).show();
            }
            else
            {
                gradeQuiz();
            }
        }
    }

    /**
     * Checks whether any/each question is left answered and
     * also updates the number of answered questions
     * @return true if any question is left unanswered and false if all questions are answered
     */
    public boolean isAnyQuestionUnanswered()
    {
        boolean anyUnanswered = false;
        setAnsweredQuestions(NUMBER_OF_QUESTIONS);

        if(q1EditText.getText().toString().isEmpty())
        {
            anyUnanswered = true;
            setAnsweredQuestions(answeredQuestions - 1);
        }

        if(!q2OptionA.isChecked() && !q2OptionB.isChecked() && !q2OptionC.isChecked() && !q2OptionD.isChecked())
        {
            anyUnanswered = true;
            setAnsweredQuestions(answeredQuestions - 1);
        }

        if(q3EditText.getText().toString().isEmpty())
        {
            anyUnanswered = true;
            setAnsweredQuestions(answeredQuestions - 1);
        }

        if(!q4OptionA.isChecked() && !q4OptionB.isChecked() && !q4OptionC.isChecked() && !q4OptionD.isChecked())
        {
            anyUnanswered = true;
            setAnsweredQuestions(answeredQuestions - 1);
        }

        if(!q5OptionA.isChecked() && !q5OptionB.isChecked() && !q5OptionC.isChecked() && !q5OptionD.isChecked())
        {
            anyUnanswered = true;
            setAnsweredQuestions(answeredQuestions - 1);
        }

        if(!q6OptionA.isChecked() && !q6OptionB.isChecked())
        {
            anyUnanswered = true;
            setAnsweredQuestions(answeredQuestions - 1);
        }

        if(!q7OptionA.isChecked() && !q7OptionB.isChecked() && !q7OptionC.isChecked() && !q7OptionD.isChecked())
        {
            anyUnanswered = true;
            setAnsweredQuestions(answeredQuestions - 1);
        }

        if(!q8OptionA.isChecked() && !q8OptionB.isChecked() && !q8OptionC.isChecked() && !q8OptionD.isChecked())
        {
            anyUnanswered = true;
            setAnsweredQuestions(answeredQuestions - 1);
        }

        return anyUnanswered;
    }

    /**
     * Calculate the resulting score of the quiz.
     */
    void gradeQuiz()
    {
        int score = 0;

        // calculate and increment the score based on each correct answer
        score += checkQuestion1Answer();
        score += checkQuestion2Answer();
        score += checkQuestion3Answer();
        score += checkQuestion4Answer();
        score += checkQuestion5Answer();
        score += checkQuestion6Answer();
        score += checkQuestion7Answer();
        score += checkQuestion8Answer();

        String message = getString(R.string.toast_score_message, score, NUMBER_OF_QUESTIONS);

        int bestScore = loadBestScore();

        if (score > bestScore)
        {
            message += "\nNew Best: " + score;
            saveBestScore(score);
            setRatings(calculateBestRating());
        }
        else
        {
            message += "\nBest: " + bestScore;
        }

        // display the results of the quiz session
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

        // set the quiz state to be finished
        finished = true;

        // change the button text based on the quiz state
        resetButton();
    }

    /**
     * Calculates the best or highest ever ratings based on quiz sessions.
     * @return the previous highest rating
     */
    float calculateBestRating()
    {
        return ((float)loadBestScore() / (float)NUMBER_OF_QUESTIONS) * (float)NUMBER_OF_STARS;
    }

    /**
     * Resets the button, answered questions and all questions view groups to their default values
     */
    void reset()
    {
        setAnsweredQuestions(0);

        resetMark(q1Mark);
        resetMark(q2Mark);
        resetMark(q3Mark);
        resetMark(q4Mark);
        resetMark(q5Mark);
        resetMark(q6Mark);
        resetMark(q7Mark);
        resetMark(q8Mark);

        resetQuestion1();
        resetQuestion2();
        resetQuestion3();
        resetQuestion4();
        resetQuestion5();
        resetQuestion6();
        resetQuestion7();
        resetQuestion7();
        resetQuestion7();
        resetQuestion8();

        finished = false;
        resetButton();
    }

    /**
     * Modifies the text and text colour values of the Text View
     * @param textView the TextView reference to modify
     */
    void resetMark(TextView textView)
    {
        // set the text to be empty
        textView.setText("");
        // set the colour to be white using the plain HEX value,
        // where the alpha-channel is first, then the colour value
        textView.setTextColor(0xFFFFFFFF);
    }

    /**
     * Loads the best score from storage
     * @return the previously saved highest score
     */
    int loadBestScore()
    {
        // get handle to shared preferences
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        // use save key-data to read from shared preferences
        return sharedPref.getInt(getString(R.string.best_score_key), 0);
    }

    /**
     * Saves the latest highest score to storage
     * @param newBestScore latest highest score
     */
    void saveBestScore(int newBestScore)
    {
        // get handle to shared preferences
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        // prepare to edit data shared preferences
        SharedPreferences.Editor editor = sharedPref.edit();
        // use save key-data to write to shared preferences
        editor.putInt(getString(R.string.best_score_key), newBestScore);
        // save changes asynchronously and update to disk
        editor.apply();
    }

    /**
     * Text changed listener for all edit text view groups in the quiz
     */
    private TextWatcher textChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
            // update any changes to the answered questions value when an edit text value has been changed
            //isAnyQuestionUnanswered();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            // update any changes to the answered questions value when an edit text value has been changed
            isAnyQuestionUnanswered();
        }

        @Override
        public void afterTextChanged(Editable s)
        {
            // update any changes to the answered questions value when an edit text value has been changed
            //isAnyQuestionUnanswered();
        }
    };

    /**
     * Radio button on checked changed listener for all radio buttons in the quiz
     */
    private RadioGroup.OnCheckedChangeListener radioButtonCheckedListener = new RadioGroup.OnCheckedChangeListener()
    {
        public void onCheckedChanged(RadioGroup group, int checkedId)
        {
            // update any changes to the answered questions value when a radio button state is checked
            isAnyQuestionUnanswered();
        }
    };

    /**
     * Checkbox on changed listener for all check boxes in the quiz
     */
    private CheckBox.OnCheckedChangeListener checkBoxOnCheckedListener = new CheckBox.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            // update any changes to the answered questions value when a checkbox state is changed
            isAnyQuestionUnanswered();
        }
    };

    /**
     * Quit and exit the application.
     */
    void quitApp()
    {
        // drop activity from memory
        finish();

        // kill the current activity
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);

        System.exit(0);
    }
}
