package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private final List<E> list = new ArrayList<>();
    private final ReentrantReadWriteLock.ReadLock readLock =
            new ReentrantReadWriteLock().readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock =
            new ReentrantReadWriteLock().writeLock();

    public void add(E element) {
        writeLock.lock();
        try {
            list.add(element);
        } finally {
            writeLock.unlock();
        }
    }

    public E get(int index) {
        readLock.lock();
        try {
            return list.get(index);
        } finally {
            readLock.unlock();
        }
    }

    public int size() {
        readLock.lock();
        try {
            return list.size();
        } finally {
            readLock.unlock();
        }
    }
}
