package team.hdt.neutronia.base.utils;

import javax.annotation.Nullable;

public interface IntIterable<T> extends Iterable<T> {
   @Nullable
   T getInt(int var1);
}
