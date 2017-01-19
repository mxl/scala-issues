public abstract class RenameJava extends ConcreteJava {
    public Object concreteTest(Class<?> c) {
        return super.test(c);
    }

    abstract protected Object renameTest(Class<?> c);

    @Override
    protected Object test(Class<?> c) {
        return renameTest(c);
    }
}
