package echopoint.model;

import java.util.List;

public interface AutoLookupSelectFieldModel extends AutoLookupSelectModel
{
  /**
    * Returns a list by a parameter 'searchString'.
    */
  List<EntrySelect> searchEntries( String searchString );
}
