package com.app.demo.view;

import android.text.ParcelableSpan;

import java.util.regex.Matcher;

public interface SpanCreator {
    ParcelableSpan create(Matcher m);
}
