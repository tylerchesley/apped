package io.tylerchesley.apped.debug.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.security.InvalidParameterException;

import io.tylerchesley.apped.R;
import io.tylerchesley.apped.debug.setting.Setting;
import io.tylerchesley.rendered.factory.RendererFactory;
import io.tylerchesley.rendered.renderer.Renderer;

public class SettingsRendererFactory<S extends Setting> implements RendererFactory<S> {

    @Override
    public Renderer<S> createRenderer(ViewGroup group, int type) {
        final View view = LayoutInflater.from(group.getContext()).inflate(type, group, false);
        if (type == R.layout.list_item_immutable_setting) {
            return (Renderer<S>)  new ImmutableSettingRenderer(view);
        }
        else if (type == R.layout.list_item_section) {
            return (Renderer<S>) new SectionRenderer(view);
        }
        else if (type == R.layout.list_item_application_header) {
            return (Renderer<S>) new ApplicationHeaderRenderer(view);
        }
        else if (type == R.layout.list_item_boolean_setting) {
            return (Renderer<S>) new BooleanSettingRenderer(view);
        }
        else if (type == R.layout.list_item_single_choice_setting) {
            return (Renderer<S>) new ChoiceSettingRenderer(view);
        }
        else {
            throw new InvalidParameterException("No renderer found for " + type);
        }
    }

}
