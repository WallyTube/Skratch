package me.wally.skratch.client.ui;

import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.container.GridLayout;

public class PingPongScreen extends BaseUIModelScreen<FlowLayout> {

    public PingPongScreen() {
        super(FlowLayout.class, DataSource.file("testtmp/PingPongScreen.xml"));
    }

    @Override
    protected void build(FlowLayout rootComponent) {

    }

}
