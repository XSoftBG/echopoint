package echopoint.model;

import java.util.EventListener;

public interface ComboBoxModel extends AutoLookupSelectModel
{
	interface Listener extends EventListener
  {
    public void entriesListChanged();
  }

  public void addComboBoxModelListener(Listener l);
  public void removeComboBoxModelListener(Listener l);
}
