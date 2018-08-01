package com.example.netvmeettyb.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.netvmeettyb.R;
import com.example.netvmeettyb.msg.util.MsgData;
import com.example.netvmeettyb.service.MyApplication;
import com.example.netvmeettyb.service.MyServiceZHY;
import com.vmeet.netsocket.data.Row;
import com.vmeet.netsocket.tool.DateTool;
public class TestChatActivity extends Activity {

    // 聊天信息输入框
    private EditText mInputEdit;
    // 发送按钮
    private Button mSendBtn;
   // private Tbl tbl;
    private String currMac;
  //  private Tbl userTbl;
  //  private Tbl recentbl;
 //   private BaseAdapter myAdapter;
  //  private Activity context;
    // 显示内容的 TextView
    private TextView mContentText;
    private String  sendMac;
  //  private boolean isGroup; //是否是群聊
  //  private MsgSendHelper sendHelper;

    public TestChatActivity() {
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_chat);
        initView();
        Intent intent = new Intent(TestChatActivity.this,MyServiceZHY.class);
        startService(intent);
        sendMac="FF-0F-30-48-11-63";
      // sendHelper = new MsgSendHelper();
        mSendBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String str = mInputEdit.getText().toString();
                Row row = new Row();
                creatRow(row,str);
                MsgData.sendRow(row,sendMac);
            }
        });

    }
    private void creatRow(Row row,String str){

        row.setVal("rowid1", System.currentTimeMillis() + "");
        row.setVal("infoType", "txt");
        row.setVal("msgTxt", str);
        row.setVal("datetime", DateTool.convertLong2String(System.currentTimeMillis()));
        row.setVal("DoT", "send");
        row.setVal("sender", MyApplication.mac);
        row.setVal("senderName", MyApplication.myUserName);
        row.setVal("recs", "FF-0F-30-48-11-63");
        row.setVal("searchKey", "FF-0F-30-48-11-63");
        row.setVal("dirCount", "null");
    }
    private void initView() {
        mContentText=(TextView)findViewById(R.id.ec_text_content);
        mSendBtn = (Button) findViewById(R.id.ec_btn_send);
        mInputEdit = (EditText) findViewById(R.id.ec_edit_message_input);

    }

}
