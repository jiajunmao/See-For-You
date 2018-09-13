package com.app.CClient.Utlis;

import android.widget.EditText;

import java.util.regex.Pattern;

public enum EditTextStyle implements IEditTextStyle {

	/**
	 * 邮箱判断
	 */
	EMAIL() {
		@Override
		public boolean check(String msg) {
			return false;
		}

		@Override
		public int getErrorId() {
			return 0;
		}

		@Override
		public IEditTextStyle getEntity(Object... params) {
			return this;
		}
	},
	
	BOUND(){
		@Override
		public boolean check(String str) {
			return false;
		}

		@Override
		public int getErrorId() {
			return 0;
		}

		@Override
		public IEditTextStyle getEntity(Object... params) {
			return new BoundStyle((Integer)params[0], (Integer) params[1]);
		}
	},
	
	LENGTH(){

		@Override
		public boolean check(String str) {
			return false;
		}

		@Override
		public int getErrorId() {
			return 0;
		}

		@Override
		public IEditTextStyle getEntity(Object... params) {
			return new LengthStyle((Integer) params[0]);
		}
		
	},
	
	EQUAL(){

		@Override
		public boolean check(String str) {
			return false;
		}

		@Override
		public int getErrorId() {
			return 0;
		}

		@Override
		public IEditTextStyle getEntity(Object... params) {
			return new EqualStyle((EditText) params[0]);
		}
		
	},
	

	/**
	 * 手机号码检验
	 */
	PHONE() {
		@Override
		public boolean check(String msg) {
			return Pattern.matches("^1[3|4|5|8|7]\\d{9}$", msg);
		}

		@Override
		public int getErrorId() {
			return ERROR_PHONE_FORMAT;
		}

		@Override
		public IEditTextStyle getEntity(Object... params) {
			return this;
		}
	},

	/**
	 * 密码
	 */
	PASSWORD(){

		@Override
		public boolean check(String msg) {
			//判断密码是否包含数字：包含返回1，不包含返回0
			int i = msg.matches(".*\\d+.*") ? 1 : 0;

			//判断密码是否包含字母：包含返回1，不包含返回0
			int j = msg.matches(".*[a-zA-Z]+.*") ? 1 : 0;

			//判断密码是否包含特殊符号(~!@#$%^&*()_+|<>,.?/:;'[]{}\)：包含返回1，不包含返回0
			int k = msg.matches(".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*") ? 1 : 0;


			if (i + j + k < 2 ) {
				return false;
			}else {
				return true;
			}
		}

		@Override
		public int getErrorId() {
			return ERROR_PHONE_FORMAT;
		}

		@Override
		public IEditTextStyle getEntity(Object... params) {
			return this;
		}
	}
    ;
	
	
	class BoundStyle implements IEditTextStyle{
		int min,max;
		public BoundStyle(int min,int max) {
			this.min = min;
			this.max = max;
		}
		
		@Override
		public boolean check(String str) {
			return str.length() >= min && str.length() <= max;
		}

		@Override
		public int getErrorId() {
			return ERROR_BOUND;
		}

		@Override
		public IEditTextStyle getEntity(Object... params) {
			return null;
		}
	}
	
	class LengthStyle implements IEditTextStyle{
		int length;
		public LengthStyle(int length) {
			this.length = length;
		}
		
		@Override
		public boolean check(String str) {
			return str.length() == length;
		}

		@Override
		public int getErrorId() {
			return ERROR_LENGTH;
		}

		@Override
		public IEditTextStyle getEntity(Object... params) {
			return null;
		}
	}
	
	class EqualStyle implements IEditTextStyle{

		EditText et;
		
		public EqualStyle(EditText et) {
			this.et = et;
		}
		
		@Override
		public boolean check(String str) {
			return str.equals(et.getText().toString());
		}

		@Override
		public int getErrorId() {
			return ERROR_EQUAL;
		}

		@Override
		public IEditTextStyle getEntity(Object... params) {
			return null;
		}
		
	}
	
	public static final int ERROR_EMPTY = 0;
	public static final int ERROR_PHONE_FORMAT = 1;
	public static final int ERROR_EMAIL_FORMAT = 2;
	public static final int ERROR_LENGTH = 3;
	public static final int ERROR_BOUND = 4;
	public static final int ERROR_EQUAL = 5;
	public static final int ERROR_EQUALs = 6;
	

}
