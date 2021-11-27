package com.example.androidcaculatorform.model;

import android.util.Log;

import com.example.androidcaculatorform.R;

public class CalculatorModel {
    private double firstArg;
    private double secondArg;

    private final StringBuilder inputStr = new StringBuilder();

    private int actionSelected;

    private State state;

    private enum State {
        firstArgInput,
        operationSelected,
        secondArgInput,
        resultShow
    }

    public CalculatorModel() {
        state = State.firstArgInput;
    }

    public void onNumPressed(int buttonId) {

        if (state == State.resultShow) {
            state = State.firstArgInput;
            inputStr.setLength(0);
        }

        if (state == State.operationSelected) {
            state = State.secondArgInput;
            inputStr.setLength(0);
        }

        if (inputStr.length() < 9) {
            switch (buttonId) {
                case R.id.button_zero:
                    inputStr.append("0");
                    break;
                case R.id.button_one:
                    inputStr.append("1");
                    break;
                case R.id.button_two:
                    inputStr.append("2");
                    break;
                case R.id.button_three:
                    inputStr.append("3");
                    break;
                case R.id.button_four:
                    inputStr.append("4");
                    break;
                case R.id.button_five:
                    inputStr.append("5");
                    break;
                case R.id.button_six:
                    inputStr.append("6");
                    break;
                case R.id.button_seven:
                    inputStr.append("7");
                    break;
                case R.id.button_eight:
                    inputStr.append("8");
                    break;
                case R.id.button_nine:
                    inputStr.append("9");
                    break;

            }
        }

    }

    public void onActionPressed(int actionId) {
        if (actionId == R.id.button_equal && state == State.secondArgInput && inputStr.length() > 0) {
            secondArg = Integer.parseInt(inputStr.toString());
            state = State.resultShow;
            inputStr.setLength(0);
            switch (actionSelected) {
                case R.id.button_plus:
                    inputStr.append(firstArg + secondArg);
                    break;
                case R.id.button_minus:
                    inputStr.append(firstArg - secondArg);
                    break;
                case R.id.button_multiplication:
                    inputStr.append(firstArg * secondArg);
                    break;
                case R.id.button_division:
                    inputStr.append(firstArg / secondArg);
                    break;
            }

        } else if (inputStr.length() > 0 && state == State.firstArgInput && actionId != R.id.button_equal) {
            firstArg = Integer.parseInt(inputStr.toString());
            state = State.operationSelected;
            actionSelected = actionId;
        }
    }

    public String getText() {
        StringBuilder str = new StringBuilder();
        switch (state) {
            default:
                return inputStr.toString();
            case operationSelected:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .toString();
            case secondArgInput:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(inputStr)
                        .toString();
            case resultShow:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(secondArg)
                        .append(" = ")
                        .append(inputStr.toString())
                        .toString();
        }
    }

    private char getOperationChar() {
        switch (actionSelected) {
            case R.id.button_plus:
                return '+';
            case R.id.button_minus:
                return '-';
            case R.id.button_multiplication:
                return '*';
            case R.id.button_division:
            default:
                return '/';

        }
    }

    public void reset() {
        state = State.firstArgInput;
        inputStr.setLength(0);
    }
}
