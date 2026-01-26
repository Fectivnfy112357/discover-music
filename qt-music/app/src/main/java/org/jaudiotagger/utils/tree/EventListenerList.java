package org.jaudiotagger.utils.tree;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.EventListener;

/* loaded from: classes3.dex */
public class EventListenerList implements Serializable {
    private static final Object[] NULL_ARRAY = new Object[0];
    private static final long serialVersionUID = 8528835753144198230L;
    protected transient Object[] listenerList = NULL_ARRAY;

    public Object[] getListenerList() {
        return this.listenerList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T extends EventListener> T[] getListeners(Class<T> cls) {
        Object[] objArr = this.listenerList;
        T[] tArr = (T[]) ((EventListener[]) Array.newInstance((Class<?>) cls, getListenerCount(objArr, cls)));
        int i = 0;
        for (int length = objArr.length - 2; length >= 0; length -= 2) {
            if (objArr[length] == cls) {
                tArr[i] = (EventListener) objArr[length + 1];
                i++;
            }
        }
        return tArr;
    }

    public int getListenerCount() {
        return this.listenerList.length / 2;
    }

    public int getListenerCount(Class<?> cls) {
        return getListenerCount(this.listenerList, cls);
    }

    private int getListenerCount(Object[] objArr, Class<?> cls) {
        int i = 0;
        for (int i2 = 0; i2 < objArr.length; i2 += 2) {
            if (cls == ((Class) objArr[i2])) {
                i++;
            }
        }
        return i;
    }

    public synchronized <T extends EventListener> void add(Class<T> cls, T t) {
        if (t == null) {
            return;
        }
        if (!cls.isInstance(t)) {
            throw new IllegalArgumentException("Listener " + t + " is not of type " + cls);
        }
        Object[] objArr = this.listenerList;
        if (objArr == NULL_ARRAY) {
            this.listenerList = new Object[]{cls, t};
        } else {
            int length = objArr.length;
            Object[] objArr2 = new Object[length + 2];
            System.arraycopy(objArr, 0, objArr2, 0, length);
            objArr2[length] = cls;
            objArr2[length + 1] = t;
            this.listenerList = objArr2;
        }
    }

    public synchronized <T extends EventListener> void remove(Class<T> cls, T t) {
        if (t == null) {
            return;
        }
        if (!cls.isInstance(t)) {
            throw new IllegalArgumentException("Listener " + t + " is not of type " + cls);
        }
        int length = this.listenerList.length - 2;
        while (true) {
            if (length < 0) {
                length = -1;
                break;
            }
            Object[] objArr = this.listenerList;
            if (objArr[length] == cls && objArr[length + 1].equals(t)) {
                break;
            } else {
                length -= 2;
            }
        }
        if (length != -1) {
            Object[] objArr2 = this.listenerList;
            int length2 = objArr2.length - 2;
            Object[] objArr3 = new Object[length2];
            System.arraycopy(objArr2, 0, objArr3, 0, length);
            if (length < length2) {
                System.arraycopy(this.listenerList, length + 2, objArr3, length, length2 - length);
            }
            if (length2 == 0) {
                objArr3 = NULL_ARRAY;
            }
            this.listenerList = objArr3;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        Object[] objArr = this.listenerList;
        objectOutputStream.defaultWriteObject();
        for (int i = 0; i < objArr.length; i += 2) {
            Class cls = (Class) objArr[i];
            EventListener eventListener = (EventListener) objArr[i + 1];
            if (eventListener != null && (eventListener instanceof Serializable)) {
                objectOutputStream.writeObject(cls.getName());
                objectOutputStream.writeObject(eventListener);
            }
        }
        objectOutputStream.writeObject(null);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        this.listenerList = NULL_ARRAY;
        objectInputStream.defaultReadObject();
        while (true) {
            Object object = objectInputStream.readObject();
            if (object == null) {
                return;
            }
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            add(Class.forName((String) object, true, contextClassLoader), (EventListener) objectInputStream.readObject());
        }
    }

    public String toString() {
        Object[] objArr = this.listenerList;
        String str = "EventListenerList: " + (objArr.length / 2) + " listeners: ";
        for (int i = 0; i <= objArr.length - 2; i += 2) {
            str = (str + " type " + ((Class) objArr[i]).getName()) + " listener " + objArr[i + 1];
        }
        return str;
    }
}
