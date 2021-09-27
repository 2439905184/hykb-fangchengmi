package com.littlesandbox.hykb;

import android.content.pm.ActivityInfo;
import android.widget.Toast;

import com.m3839.union.fcm.UnionFcmListener;
import com.m3839.union.fcm.UnionFcmParam;
import com.m3839.union.fcm.UnionFcmSDK;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;

import java.util.ArrayList;
import java.util.List;
//这份代码是godot3.2.x(3.2.2)使用的
public class Hykb extends GodotPlugin
{

    public Hykb(Godot godot) {
        super(godot);
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
        UnionFcmParam param = new UnionFcmParam.Builder()
                .setContact("test@qq.com")
                .setGameId(gameId)
                .setOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .build();
        UnionFcmSDK.initSDK(getActivity(), param, new UnionFcmListener() {
            @Override
            public void onFcm(int code, String message) {
                if(2005 == code) {
                    getActivity().finish();
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run()
                        {
                            Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
    }
