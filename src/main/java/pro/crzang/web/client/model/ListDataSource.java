package pro.crzang.web.client.model;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.*;
import com.google.inject.Inject;
import com.google.inject.Provider;

import java.util.*;

/**
 * Класс, описываюший список данных.
 *
 * @param <E> тип элментов списка
 * @author Alexey
 */
class ListDataSource<E> extends AbstractDataSource implements List<E> {

  @Inject
  private Provider<ListDataSource> listDataSourceProvider;
  private Class<?> objectType;
  private Map<Integer, Object> cache = new HashMap<>();;

  @Inject
  ListDataSource() {
    super(DataSourceType.ARRAY, "", new JSONObject());
  }

  /**
   * Конструктор. с
   *
   * @param name      имя объекта в качестве поля для вышележашего объекта
   * @param jsonArray данные
   */
  /*public ListDataSource(String name, JSONArray jsonArray) {
    super(DataSourceType.ARRAY, name, jsonArray);
    cache = new HashMap<>();
  }*/

  /**
   * Конструктор.
   *
   * @param name       имя объекта в качестве поля для вышележашего объекта
   * @param jsonArray  данные
   * @param objectType класс медиатипа - элмента списка
   */
  /*public ListDataSource(String name, JSONArray jsonArray, Class<?> objectType) {
    super(DataSourceType.ARRAY, name, jsonArray);
    this.objectType = objectType;
    cache = new HashMap<>();
  }*/

  /**
   * Конструктор.
   *
   * @param name имя
   * @param list данные
   */
  /*public ListDataSource(String name, List<E> list) {
    super(DataSourceType.ARRAY, name, new JSONArray());
    cache = new HashMap<>();
    if (list instanceof AbstractDataSource)
      this.value = ((AbstractDataSource) list).value;
    else
      for (E element : list)
        add(element);
  }*/

  /**
   * Конструктор.
   *
   * @param name       имя
   * @param list       данные
   * @param objectType тип
   */
  /*public ListDataSource(String name, List<E> list, Class<?> objectType) {
    super(DataSourceType.ARRAY, name, new JSONArray());
    this.objectType = objectType;
    cache = new HashMap<>();
    if (list instanceof AbstractDataSource)
      this.value = ((AbstractDataSource) list).value;
    else
      for (E element : list)
        add(element);
  }*/

  @Override
  protected JSONValue init(Object obj) {
    return (JSONValue) obj;
  }

  /**
   * Добавление элмента.
   *
   * @param item элмент
   * @return успешность добавления элмента.
   */
  @Override
  public boolean add(E item) {
    add(size(), item);
    return true;
  }

  /**
   * Получение элмента.
   *
   * @param index индекс элмента, который надо получить
   * @return элмент
   */
  @Override
  public E get(int index) {
    if (index > size())
      throw new IndexOutOfBoundsException("Не возможно получить" + index + "элемент");
    if (cache.containsKey(index))
      return (E) cache.get(index);
    JSONValue value = this.value.isArray().get(index);
    E element = getElement(value);
    cache.put(index, element);
    return element;
  }

  /**
   * Формирование элемента в виде AbstractDataSource.
   *
   * @param value json значение
   * @return соответсвуюшее AbstractDataSource
   */
  private E getDataSourceElement(JSONValue value) {
    E result = null;
    if (objectType == null)
      return null;
    if (objectType.equals(AbstractDataSource.class))
      result = (E) dataSourceService.createDataSource("", value);
    else if (objectType.equals(NumberDataSource.class) || objectType.equals(StringDataSource.class)
        || objectType.equals(BooleanDataSource.class) || objectType.equals(ListDataSource.class))
      result = (E) beanFactoryMapping.create(objectType, "", value);
    return result;
  }

  /**
   * Получение элмента из его значения.
   *
   * @param value значение
   * @return элмент
   */
  private E getElement(JSONValue value) {
    E result = null;
    E dataSourceElement = getDataSourceElement(value);
    if (dataSourceElement != null)
      result = dataSourceElement;
    else if (value == null || value.isNull() != null)
      return null;
    else if (objectType != null) {
      Provider<? extends Bean> factory = beanFactoryMapping.getFactory(objectType);
      if (factory != null)
        result = (E) factory.get().setValue(value);
      else if (value.isObject() != null)
        result = (E) new ObjectDataSource().setValue(value.isObject());
    }
    if (result == null) {
      if (value.isNumber() != null) {
        Double doubleValue = value.isNumber().doubleValue();
        if (Double.class.equals(objectType) || doubleValue % 1 != 0)
          result = (E) doubleValue;
        else if (Long.class.equals(objectType) || doubleValue > Integer.MAX_VALUE
            || doubleValue < Integer.MIN_VALUE)
          result = (E) new Long(doubleValue.longValue());
        else
          result = (E) new Integer(doubleValue.intValue());
      } else if (value.isString() != null)
        result = (E) value.isString().stringValue();
      else if (value.isBoolean() != null)
        result = (E) Boolean.valueOf(value.isBoolean().booleanValue());
      else if (value.isArray() != null)
        result = (E) listDataSourceProvider.get().setValue(value.isArray());
      else if (value.isObject() != null) {
        result = (E) new ObjectDataSource().setValue(value.isObject());
      } else
        throw new DataSourceStructureException("Невозможно получить элемент " + value
            + ", тип элемента не поддерживается");
    }
    return result;
  }

  /**
   * Очистка списка.
   */
  @Override
  public void clear() {
    JSONArray jsonArray = value.isArray();
    removeArrayElements(jsonArray.getJavaScriptObject(), 0, jsonArray.size());
    cache.clear();
  }

  /**
   * Получение индекса элмента.
   *
   * @param o элмент
   * @return индекс
   */
  @Override
  public int indexOf(Object o) {
    JSONArray array = value.isArray();
    if (o == null) {
      for (int i = 0; i < array.size(); i++)
        if (array.get(i).isNull() != null)
          return i;
    } else {
      for (int i = 0; i < array.size(); i++) {
        if (o.equals(get(i)))
          return i;
      }
    }
    return -1;
  }

  /**
   * Проверка наличия элмента в списке.
   *
   * @param o элмент
   * @return содержится/несодержится
   */
  @Override
  public boolean contains(Object o) {
    return indexOf(o) >= 0;
  }

  /**
   * Проверка списка на пустоту.
   *
   * @return пустой/непустой
   */
  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  /**
   * Удаление элмента из списка по указанному индексу.
   *
   * @param index индекс
   * @return удаленный элмент
   */
  @Override
  public E remove(int index) {
    E element = get(index);
    removeArrayElements(value.isArray().getJavaScriptObject(), index, 1);
    cache.remove(index);
    Map<Integer, Object> tempCache = new HashMap<>();
    tempCache.putAll(cache);
    for (Integer key : tempCache.keySet()) {
      if (key > index) {
        Object cacheElement = tempCache.get(key);
        cache.remove(key);
        cache.put(key - 1, cacheElement);
      }
    }
    return element;
  }

  /**
   * Удаление элментов из массива.
   *
   * @param array    массив
   * @param startIdx индекс элмента, с котрого стоит начать удаление
   * @param count    количество элментов, которые необходимо удалить
   */
  private native void removeArrayElements(JavaScriptObject array, int startIdx, int count)
  /*-{
    array.splice(startIdx, count);
  }-*/;

  /**
   * Получение размера списка.
   *
   * @return размер списка
   */
  @Override
  public int size() {
    if (value.isArray() != null)
      return this.value.isArray().size();
    else
      throw new DataSourceStructureException("Невозможно получить  длину массива: объект "
          + getDsName() + " не является целым массивом");
  }

  @Override
  public void add(int index, E item) {
    JSONArray array = value.isArray();
    int size = size();
    for (int i = size - 1; i >= index; i--) {
      array.set(i + 1, array.get(i));
      if (cache.containsKey(i)) {
        Object cacheElement = cache.get(i);
        cache.remove(cacheElement);
        cache.put(i + 1, cacheElement);
      }
    }
    set(index, item);
  }

  @Override
  public boolean addAll(Collection<? extends E> arg0) {
    int newElementCount = 0;
    for (E element : arg0) {
      add(element);
      newElementCount = newElementCount + 1;
    }
    return newElementCount != 0;
  }

  @Override
  public boolean addAll(int arg0, Collection<? extends E> arg1) {
    int newElementCount = size();
    for (E element : arg1) {
      add(newElementCount + arg0, element);
      newElementCount = newElementCount + 1;
    }

    return newElementCount != 0;
  }

  @Override
  public boolean containsAll(Collection<?> arg0) {
    if (arg0 != null) {
      Iterator<?> iterator = arg0.iterator();
      while (iterator.hasNext()) {
        if (!contains(iterator.next()))
          return false;
      }
    }
    return true;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iter();
  }

  @Override
  public int lastIndexOf(Object arg0) {
    int size = size();
    JSONArray array = value.isArray();
    if (arg0 == null) {
      for (int i = size - 1; i >= 0; i--) {
        if (array.get(i).isNull() != null)
          return i;
      }
    } else {
      for (int i = size - 1; i >= 0; i--) {
        if (get(i).equals(arg0))
          return i;
      }
    }
    return -1;
  }

  @Override
  public ListIterator<E> listIterator() {
    return new ListItr(0);
  }

  @Override
  public boolean remove(Object o) {
    int index = indexOf(o);
    return remove(index) != null;
  }

  @Override
  public boolean removeAll(Collection<?> arg0) {
    boolean result = false;
    for (Object element : arg0) {
      result = result && remove(element);
    }
    return result;
  }

  @Override
  public boolean retainAll(Collection<?> arg0) {

    return false;
  }

  @Override
  public String toJson() {
    return toJson(value.isArray().getJavaScriptObject());
  }

  @Override
  public ListDataSource asList() {
    return this;
  }

  ;

  @Override
  /**
   * Клонирование объекта.
   * @return клонированный объект.
   */
  public ListDataSource<E> cloneBean() {
    return (ListDataSource<E>) listDataSourceProvider.get().setName(this.getDsName()).setValue(
        value.isArray());
  }

  @Override
  public E set(int index, E item) {
    JSONArray array = this.value.isArray();
    JSONValue jsonValue = null;
    JSONValue oldItem = JSONNull.getInstance();
    if (item == null)
      jsonValue = JSONNull.getInstance();
    else if (item instanceof AbstractDataSource)
      jsonValue = ((AbstractDataSource) item).value;
    else if (item instanceof String)
      jsonValue = new JSONString((String) item);
    else if (item instanceof Double)
      jsonValue = new JSONNumber((Double) item);
    else if (item instanceof Integer)
      jsonValue = new JSONNumber(((Integer) item).doubleValue());
    else if (item instanceof Long)
      jsonValue = new JSONNumber(((Long) item).doubleValue());
    else if (item instanceof Boolean)
      jsonValue = JSONBoolean.getInstance((Boolean) item);
    else if (item instanceof ObjectDataSource)
      jsonValue = ((ObjectDataSource) item).value;
    else
      new DataSourceStructureException("Невозможно добавить элмент: " + item
          + " ,тип элмента не  поддерживается.");
    oldItem = array.set(index, jsonValue);
    // CMJ-15293
    // Map<Integer, Object> tempCache = new HashMap<Integer, Object>();
    // tempCache.putAll(cache);
    cache.put(index, item);
    // for (Integer key : tempCache.keySet()) {
    // if (key >= index) {
    // Object cacheElement = tempCache.get(key);
    // cache.put(key + 1, cacheElement);
    // }
    // }
    return getElement(oldItem);
  }

  @Override
  public List<E> subList(int arg0, int arg1) {
    return null;
  }

  @Override
  public Object[] toArray() {
    int size = size();
    Object[] array = new Object[size];
    for (int i = 0; i < size; i++) {
      array[i] = get(i);
    }
    return array;
  }

  @Override
  public ListIterator<E> listIterator(int arg0) {
    return new ListItr(arg0);
  }

  @Override
  public <T> T[] toArray(T[] arg0) {
    return toArray(arg0);
  }

  /**
   * Является ли текущий объект массивом.
   *
   * @return true - если текущий объект является массивом, false - иначе.
   */
  @Override
  public boolean isArray() {
    return true;
  }

  public <E> ListDataSource<?> setObjectType(Class<E> objectType) {
    this.objectType = objectType;
    return this;
  }

  /**
   * Фабрика оболочки списка элементов.
   *
   * @author Ivan Chernyshihin
   * @since 4.3
   */
 /* public static class ListDataSourceFactory implements BeanFactory {

    @Override
    public AbstractDataSource create(String name, JSONValue jsonValue) {
      if (jsonValue == null || jsonValue.isNull() != null)
        return NullDataSource.getInstance();
      if (jsonValue.isArray() == null)
        throw new DataSourceStructureException(
            "Невозможно созать оболочку массива, объект не  является массивом");
      ListDataSource<AbstractDataSource> list = new ListDataSource<>("",
          jsonValue.isArray());
      return list;
    }
  }*/

  /**
   * Итератор.
   *
   * @author Ivan Chernyshihin
   * @since 4.3
   */
  private class Iter implements Iterator<E> {

    int cursor;
    int lastRet = -1;

    @Override
    public boolean hasNext() {
      return cursor != size();
    }

    @Override
    public E next() {
      int i = cursor++;
      lastRet = i;
      return get(i);
    }

    @Override
    public void remove() {
      if (lastRet < 0)
        throw new IllegalStateException();
      try {
        ListDataSource.this.remove(lastRet);
        cursor = lastRet;
        lastRet = -1;

      } catch (IndexOutOfBoundsException ex) {
        throw new ConcurrentModificationException();
      }
    }
  }

  /**
   * Итератор для списка.
   *
   * @author Ivan Chernyshihin
   * @since 4.3
   */
  private class ListItr extends Iter implements ListIterator<E> {
    private JSONArray array = value.isArray();
    ;

    /**
     * Конструктор.
     *
     * @param index индекс элмента
     */
    ListItr(int index) {
      super();
      cursor = index;
    }

    @Override
    public void add(E arg0) {
      try {
        int i = cursor;
        ListDataSource.this.add(i, arg0);
        cursor = i + 1;
        lastRet = -1;
      } catch (IndexOutOfBoundsException ex) {
        throw new ConcurrentModificationException();
      }
    }

    @Override
    public boolean hasPrevious() {
      return cursor != 0;
    }

    @Override
    public int nextIndex() {
      return cursor;
    }

    @Override
    public E previous() {
      int i = cursor - 1;
      if (i < 0)
        throw new NoSuchElementException();

      if (i >= array.size())
        throw new ConcurrentModificationException();
      cursor = i;
      return get(lastRet = i);
    }

    @Override
    public int previousIndex() {
      return cursor - 1;
    }

    @Override
    public void set(E arg0) {
      if (lastRet < 0)
        throw new IllegalStateException();
      try {
        ListDataSource.this.set(lastRet, arg0);
      } catch (IndexOutOfBoundsException ex) {
        throw new ConcurrentModificationException();

      }
    }
  }
}
