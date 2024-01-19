package com.jibbow.fastis;

import com.jibbow.fastis.rendering.AbstractAppointmentFactory;
import com.jibbow.fastis.rendering.DayViewRenderer;
import com.jibbow.fastis.rendering.FlexAppointmentFactory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Jibbow on 8/12/17.
 */
public class DayView extends WeekView {


    public DayView() {
        this(LocalDate.now(), new Calendar());
    }
    public DayView(LocalDate date, Calendar... calendar) {
        this(new SimpleObjectProperty<>(date), calendar);
    }

    public DayView(ObjectProperty<LocalDate> date, Calendar... calendar) {
        this(date, LocalTime.MIN, LocalTime.MAX, new DayViewRenderer(), new FlexAppointmentFactory(), calendar);
    }

    public DayView(ObjectProperty<LocalDate> date, LocalTime dayStartTime, LocalTime dayEndTime, DayViewRenderer renderer, AbstractAppointmentFactory appointmentFactory, Calendar... calendar) {
        super(date, 1, dayStartTime, dayEndTime, renderer, appointmentFactory, calendar);
    }
}
