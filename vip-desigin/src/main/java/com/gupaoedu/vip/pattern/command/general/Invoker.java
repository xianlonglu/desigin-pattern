package com.gupaoedu.vip.pattern.command.general;

//请求者
public class Invoker {
    private ICommand mCmd;

    public Invoker(ICommand cmd) {
        this.mCmd = cmd;
    }

    public void action() {
        this.mCmd.execute();
    }
}