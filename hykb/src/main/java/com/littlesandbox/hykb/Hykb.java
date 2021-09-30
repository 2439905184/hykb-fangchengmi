package com.littlesandbox.hykb;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.widget.Toast;

import com.m3839.union.fcm.UnionFcmListener;
import com.m3839.union.fcm.UnionFcmParam;
import com.m3839.union.fcm.UnionFcmSDK;
import com.m3839.union.fcm.UnionFcmUser;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;

import java.util.ArrayList;
import java.util.List;
//这份代码是godot3.2.x(3.2.2)使用的
public class Hykb extends GodotPlugin
{
    public Context ctx;
    public Hykb(Godot godot) {
        super(godot);
        ctx = getActivity().getApplicationContext();
    }
    @Override
    public String getPluginName() {
        return "Hykb";
    }

    @Override
    public List<String> getPluginMethods() {
        ArrayList<String> methods = new ArrayList<String>();
        methods.add("init");
        return methods;
    }
    //从这里传入初始化gameID 注10690是默认测试gameId
    public void init(String p_gameId)
    {
        String gameId = p_gameId;
        Log.println(6, "防沉迷", "步骤1");
        UnionFcmParam param = new UnionFcmParam.Builder().setContact("test@qq.com").setGameId(p_gameId).setOrientation(1).build();
        Log.println(6, "防沉迷", "步骤2");
        UnionFcmSDK.initSDK(getActivity(), param, new UnionFcmListener() {
            public void onFcm(int code, final String message) {
                if (2005 == code) {
                    Log.println(6, "防沉迷", "code");
                    //Hykb.this.getActivity().finish();
                    return;
                }
                Hykb.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Log.println(6, "防沉迷", message);
                    }
                });
            }
        });

    }
    }
