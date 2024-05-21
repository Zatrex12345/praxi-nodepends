package lol.vifez.praxi.util.command.command.adapter.impl;

import lol.vifez.praxi.util.command.command.adapter.CommandTypeAdapter;

public class StringTypeAdapter implements CommandTypeAdapter
{
    @Override
    public <T> T convert(final String string, final Class<T> type) {
        return type.cast(string);
    }
}