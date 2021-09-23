package model;

import model.celebration.Type;
import model.favor.Deadline;

/**
 * カード
 */
public sealed interface Card {
    Message message();

    Status status();

    /** 挨拶 */
    record Greeting(Message message, Status status) implements Card {}

    /** お祝い */
    record Celebration(Message message, Status status, Type type) implements Card {}

    /** お願い */
    record Favor(Message message, Status status, Deadline deadline) implements Card {}
}