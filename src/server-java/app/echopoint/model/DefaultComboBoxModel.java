package echopoint.model;

import java.util.EventListener;
import nextapp.echo.app.event.EventListenerList;
import java.util.ArrayList;
import java.util.List;


public class DefaultComboBoxModel implements ComboBoxModel
{
  private final EventListenerList listeners = new EventListenerList();
  protected final ArrayList<EntrySelect> entries = new ArrayList<EntrySelect> ();

  @Override
  public List<EntrySelect> getAllEntries() { return this.entries; }

  @Override
  public void addComboBoxModelListener(Listener l)
  {
    listeners.addListener(ComboBoxModel.Listener.class, l);
  }

  @Override
  public void removeComboBoxModelListener(Listener l)
  {
    listeners.removeListener(ComboBoxModel.Listener.class, l);
  }

  public void fireEntriesListChanged()
  {
    final EventListener[] cblisteners = listeners.getListeners(ComboBoxModel.Listener.class);
    for(int index = 0; index < cblisteners.length; ++index)
      ((ComboBoxModel.Listener)cblisteners[index]).entriesListChanged();
  }

  public void addEntry( EntrySelect e ) { entries.add(e); fireEntriesListChanged(); }
  public void delEntry( EntrySelect e ) { entries.remove(e); fireEntriesListChanged(); }
  public void delEntry( int no )        { entries.remove(no); fireEntriesListChanged(); }
}
