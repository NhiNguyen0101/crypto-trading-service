package com.crypto.common.utils.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * this list is unmodifiable to prevent inconsistencies between the size of
 * the list and the total count. This list must not be modifiable.
 *
 * @param <T> array element type
 */
@Getter
public class Page<T> extends ArrayList<T> {
    private final int pageSize;
    private final int pageNumber;
    private final int totalCount;

    public Page(int pageSize, int pageNumber, int totalCount, List<T> list) {
        super(list);
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.totalCount = totalCount;
    }

    public Page(int pageSize, int pageNumber) {
        super();
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.totalCount = 0;
    }

    @Override
    public boolean add(T e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final Iterator<? extends T> i = Page.super.iterator();

            @Override
            public boolean hasNext() {
                return i.hasNext();
            }

            @Override
            public T next() {
                return i.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void forEachRemaining(Consumer<? super T> action) {
                i.forEachRemaining(action);
            }
        };
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            private final ListIterator<? extends T> i = Page.super.listIterator();

            @Override
            public boolean hasNext() {
                return i.hasNext();
            }

            @Override
            public T next() {
                return i.next();
            }

            @Override
            public boolean hasPrevious() {
                return i.hasPrevious();
            }

            @Override
            public T previous() {
                return i.previous();
            }

            @Override
            public int nextIndex() {
                return i.nextIndex();
            }

            @Override
            public int previousIndex() {
                return i.previousIndex();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(T t) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(T t) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void forEachRemaining(Consumer<? super T> action) {
                i.forEachRemaining(action);
            }
        };
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListIterator<T>() {
            private final ListIterator<? extends T> i = Page.super.listIterator(index);

            @Override
            public boolean hasNext() {
                return i.hasNext();
            }

            @Override
            public T next() {
                return i.next();
            }

            @Override
            public boolean hasPrevious() {
                return i.hasPrevious();
            }

            @Override
            public T previous() {
                return i.previous();
            }

            @Override
            public int nextIndex() {
                return i.nextIndex();
            }

            @Override
            public int previousIndex() {
                return i.previousIndex();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(T t) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(T t) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void forEachRemaining(Consumer<? super T> action) {
                i.forEachRemaining(action);
            }
        };
    }

    @Override
    public String toString() {
        return "ArrayListPage{" +
                "pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", totalCount=" + totalCount +
                '}' + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Page<?> that = (Page<?>) o;
        return pageSize == that.pageSize
                && pageNumber == that.pageNumber
                && totalCount == that.totalCount
                && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pageSize, pageNumber, totalCount);
    }
}
