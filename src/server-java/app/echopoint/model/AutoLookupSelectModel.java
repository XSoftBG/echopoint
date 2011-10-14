package echopoint.model;

import java.util.List;

public interface AutoLookupSelectModel
{
  interface EntrySelect
  {
    String getValue();
    String getKey();
    String getSearchVal();
  }

  /**
    * Returns a full list.
    */
  List<EntrySelect> getAllEntries();
}
