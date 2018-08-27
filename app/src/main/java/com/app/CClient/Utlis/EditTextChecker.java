package com.app.CClient.Utlis;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.EditText;


import java.util.ArrayList;
import java.util.List;

public class EditTextChecker {

	private Context mContext;
	private SparseArray<UnitCheck> ucs;
	private FormatErrorListener listener;
	private int emptyHintResId;

	public EditTextChecker(Context context) {
		mContext = context;
		ucs = new SparseArray<UnitCheck>();
	}
	
	
	public void addCheckTask(UnitCheck uc){
		ucs.put(uc.et.getId(),uc);
	}
	
	public void addCheckTask(EditText et,IEditTextStyle ets){
		addCheckTask(et, ets,0);
	}
	
	public void addCheckTask(EditText et,IEditTextStyle ets,int resId){
		UnitCheck uc = ucs.get(et.getId());
		if(uc == null){
			uc = new UnitCheck(et);
			ucs.put(et.getId(), uc);
		}
		uc.addCheck(ets, resId);
	}
	
	public boolean check(){
		
		for (int i  = 0;i< ucs.size();i++) {
			UnitCheck unitCheck = ucs.valueAt(i);
			if(TextUtils.isEmpty(unitCheck.et.getText())){
				handlerError(unitCheck.et,EditTextStyle.ERROR_EMPTY,emptyHintResId);
				return false;
			}
			
			for (StyleUnit  style : unitCheck.sus) {
				if(!style.ets.check(unitCheck.et.getText().toString())){
					handlerError(unitCheck.et,style.ets.getErrorId(),style.resId);
					return false;
				}
			} 
		}
		
		return true;
	}
	
	
	private void handlerError(EditText et,int errorId,int resId){
		if(resId != 0)
			et.setError(mContext.getString(resId));
		
		if(listener != null){
			listener.onFormatError(et, errorId);
		}
		
		et.requestFocus();
	}
	
	
	public void setListener(FormatErrorListener l){
		listener =  l;
	}

	public void setEmptyHintResId(int resId){
		emptyHintResId = resId;
	}

	public interface FormatErrorListener{
		public void onFormatError(EditText et, int errorId);
	}
	
	public class UnitCheck{
		private EditText et;
		private List<StyleUnit>sus;
		
		public UnitCheck(EditText et) {
			this.et = et;
			this.sus = new ArrayList<StyleUnit>();
		}
		
		public void addCheck(IEditTextStyle ets,int resId){
			StyleUnit su = new StyleUnit(ets, resId);
			sus.add(su);
		}
	}
	
	public class StyleUnit{
		IEditTextStyle ets;
		Integer resId;
		public StyleUnit(IEditTextStyle ets,Integer resId) {
			this.ets = ets;
			this.resId = resId;
		}
	}
}
