/*
 * This file is part of the Echo Point Project.  This project is a
 * collection of Components that have extended the Echo Web Application
 * Framework Version 3.
 *
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 */

/** The name of the AutoLookupTextField component. */
echopoint.constants.AUTO_LOOKUP_TEXT_FIELD = "echopoint.AutoLookupTextField";

echopoint.AutoLookupTextField = Core.extend( echopoint.RegexTextField,
{
  $load: function()
  {
    Echo.ComponentFactory.registerType(
        echopoint.constants.AUTO_LOOKUP_TEXT_FIELD, this);
  },

  componentType: echopoint.constants.AUTO_LOOKUP_TEXT_FIELD
});

/** The local cache is currently not implemented */

/**
 * LookupCacheEntry is the object that is stored inside a LookupCache.
 * It basically has a value, an optional sortValue and an optional XHTML
 * repsentation of that value.  It also tracks a createdDateTime which is
 * when the object was created.
 */
echopoint.model.LookupCacheEntry = Core.extend(
{
  value: null,
  sortValue: null,
  xhtml: null,
  createdDateTime: null,

  /**
   * Create a new lookup cache entry model object.
   *
   * @param value - the value used when looking up this entry
   * @param sortValue - an optional value used to sort the entry.  This allows
   *   for sort orders other than by lookup value.
   *   If it is null, then value will be used
   * @param xhtml - an optional xhtml fragment that is used to display the
   *   matching entries.  If it is null, then value will be used
   */
  $construct: function( value, sortValue, xhtml )
  {
    this.value = value;
    this.sortValue = sortValue;
    this.xhtml = xhtml;
    this.createdDateTime = new Date();
  },

  /**
   * @return how many milliseconds old this LCE is at the time the function
   *   was called
   */
  age: function()
  {
    var now = new Date();
    return now.getTime() - this.createdDateTime.getTime();
  },

  /** @return true if the LCE is older than the max age in ms */
  hasExpired: function( maxage )
  {
    if ( maxage == -1 ) return false;

    return this.age() > maxage;
  },

  /** Clones the current object into another one */
  clone: function()
  {
    return new echopoint.model.LookupCacheEntry(
        this.value, this.sortValue, this.xhtml);
  },

  /** @return A unique key for this instance. */
  toUniqueKey: function()
  {
    var s = '';
    s += 'LCE : ';
    s += this.value;
    s += ' : ';
    s += this.sortValue;
    s += ' : ';
    s += this.xhtml;
    return s;
  },

  /** Return a string description of this instance. */
  toString: function()
  {
    var s = '';
    s += this.toUniqueKey();
    s += ' : ';
    s += this.createdDateTime;
    return s;
  }
});


echopoint.model.LookupCache = Core.extend(
{
  $static:
  {
    MATCH_ONLY_FROM_START: 1,
    MATCH_IS_CASE_SENSITIVE: 2,

    strCompare: function( strA, strB )
    {
      var a = ( strA ? strA : '' );
      var b = ( strB ? strB : '' );

      if ( a == b ) return 0;
      if ( a > b ) return 1;
      else return -1;
    },

    /**
     * A static sort function that sorts by sort valuem then value and then
     * by xhtml
     */
    defaultSort: function( entryA, entryB )
    {
      if ( entryA && entryB )
      {
        // compare based on sort value first and then on value and then on xhtml
        var rc = echopoint.model.LookupCache.strCompare(
            entryA.sortValue, entryB.sortValue);

        if ( rc == 0 )
        {
          rc = echopoint.model.LookupCache.strCompare(
              entryA.value, entryB.value);

          if ( rc == 0 )
          {
            rc = echopoint.model.LookupCache.strCompare(
                entryA.xhtml, entryB.xhtml);
          }
        }

        return rc;
      }
      else if ( entryA ) return 1;
      else return -1;
    },

    /** A static sort function that sorts by lce age */
    sortByAge: function( entryA, entryB )
    {
      if ( entryA && entryB )
      {
        // compare based on age
        var ageA = entryA.createdDateTime.getTime();
        var ageB = entryB.createdDateTime.getTime();

        if ( ageA == ageB ) return 0;
        else if ( ageA < ageB ) return -1;
        else return 1;
      }
      else if ( entryA ) return 1;
      else return -1;
    }
  },

  maxage: null,
  maxsize: null,
  matchOptions: null,
  lceEntries: new Array(),
  lceUniqueEntries: new Array(),

  /**
   * Contructs a LookupCache
   *
   * @param maxage - the maximum age in ms for cache entries. -1 means
   *   unlimited entries
   * @param maxsize - the maximum size of the cache. -1 means unlimited
   *   entries
   */
  $construct: function( maxage, maxsize, matchOptions )
  {
    this.maxage = maxage;
    this.maxsize = maxsize;
    this.matchOptions = matchOptions;
  },



  /**
   * This is called when the lce has been found to exist in the cache
   * and the new lce object (with new createdatetime) is to be replace
   * the current one.  All indexes should be updated.
   */
  replaceEntry: function( newLCE )
  {
    var uniqueKey = newLCE.toUniqueKey();
    var oldLCE;

    for ( var x in this.lceEntries )
    {
      oldLCE = this.lceEntries[x];

      if ( oldLCE.toUniqueKey() == uniqueKey )
      {
        this.lceEntries[x] = newLCE;
        break;
      }
    }

    this.lceUniqueEntries[uniqueKey] = newLCE;
  },

  /**
   * Deletes the expired entries in the cache
   * @return - the number of expired entries deleted
   */
  deleteExpiredEntries: function()
  {
    var count = 0;

    // sort by age first
    this.lceEntries.sort(echopoint.model.LookupCache.sortByAge);

    // first run through and expire any objects
    for ( var index = this.lceEntries.length - 1; index >= 0; index-- )
    {
      var lce = this.lceEntries[index];

      if ( lce.hasExpired(this.maxage) )
      {
        // update unique index as well
        var uniqueKey = lce.toUniqueKey();
        this.lceUniqueEntries[uniqueKey] = null;
        delete this.lceUniqueEntries[uniqueKey];

        delete lce;
        this.lceEntries.length = this.lceEntries.length - 1;
        count++;
      }
    }

    // back to value order
    this.lceEntries.sort(echopoint.model.LookupCache.defaultSort);
    return count;
  },

  /**
   * Called to delete the oldest entries in the cache
   *
   * @param howMany - how many old entries to delete
   */
  deleteOldestEntries: function( howMany )
  {
    var count = this.deleteExpiredEntries();
    if ( count >= howMany ) return;

    // sort by age first
    this.lceEntries.sort( echopoint.model.LookupCache.sortByAge );

    // now delete as many as we require after that
    for ( var index = this.lceEntries.length - 1; index >= 0; index-- )
    {
      if ( count >= howMany || this.lceEntries.length == 0 )
      {
        break;
      }

      var lce = this.lceEntries[index];

      // update unique index as well
      var uniqueKey = lce.toUniqueKey();
      this.lceUniqueEntries[uniqueKey] = null;
      delete this.lceUniqueEntries[uniqueKey];

      delete lce;
      this.lceEntries.length = this.lceEntries.length - 1;
      count++;
    }

    // now sort it to keep it in value order
    this.lceEntries.sort( echopoint.model.LookupCache.defaultSort );
  },


  /** Called to add an array of entries into the LC */
  insertEntries: function( lceArr )
  {
    // do we need to make room for them
    if ( this.maxsize > 0 )
    {
      var newlen = this.lceEntries.length + lceArr.length;
      if ( newlen > this.maxsize )
      {
        var howMany = newlen - this.maxsize;
        this.deleteOldestEntries(howMany);
      }
    }

    // insert/replace the  entries
    var lce;
    for ( var x in lceArr )
    {
      if ( this.maxsize > -1 && this.lceEntries.length >= this.maxsize )
      {
        break; // cant fit anymore
      }

      lce = lceArr[x];
      // do we already have this entry
      var uniqueKey = lce.toUniqueKey();
      var lceLookup = this.lceUniqueEntries[uniqueKey];
      if ( lceLookup )
      {
        this.replaceEntry(lce);
      }
      else
      {
        this.lceEntries[this.lceEntries.length] = lce;
        this.lceUniqueEntries[uniqueKey] = lce;
      }
    }
    // now sort it to keep it in value order
    this.lceEntries.sort(echopoint.model.LookupCache.defaultSort);
  },

  /**
   * Called to look into the catch for matches in the cache for a specific
   * value.
   *
   * @param lookupValue - the lookup value to use
   * @return an array of LCE entries.  If my be zero length.
   */
  findMatches: function( lookupValue )
  {
    var returnArr = [];
    var lce;
    var caseSensitive = false;
    if ( ( this.matchOptions &
           echopoint.model.LookupCache.MATCH_IS_CASE_SENSITIVE ) ==
         echopoint.model.LookupCache.MATCH_IS_CASE_SENSITIVE )
    {
      caseSensitive = true;
    }

    var matchAnywhere = true;
    if ( ( this.matchOptions &
           echopoint.model.LookupCache.MATCH_ONLY_FROM_START ) ==
         echopoint.model.LookupCache.MATCH_ONLY_FROM_START )
    {
      matchAnywhere = false;
    }

    lookupValue = '' + lookupValue;
    if ( ! caseSensitive )
    {
      lookupValue = lookupValue.toUpperCase();
    }

    // Can we optimise the lookup algorithmn to something better
    // than brute force lookup.  Is it really going to make a
    // difference?
    for ( var index = 0; index < this.lceEntries.length; index++ )
    {
      lce = this.lceEntries[index];
      var matches = false;
      var searchValue = lce.value;
      if ( ! caseSensitive )
      {
        searchValue = searchValue.toUpperCase();
      }

      if ( matchAnywhere )
      {
        matches = searchValue.indexOf( lookupValue ) != -1;
      }
      else
      {
        matches = searchValue.indexOf( lookupValue ) == 0;
      }

      if ( matches )
      {
        if ( ! lce.hasExpired(this.maxage) )
        {
          returnArr[returnArr.length] = lce.clone();
        }
      }
    }
    return returnArr;
  },

  dumpCache: function()
  {
    var str = '';
    str += 'cache len=' + this.lceEntries.length;
    str += '\n';
    str += 'unique cache len=' + this.lceUniqueEntries.length;
    str += '\n';
    for ( var x in this.lceEntries )
    {
      var entry = this.lceEntries[x];

      str += entry.toString();
      str += '\n';
    }
    return str;
  },

  dumpToElement: function( htmlE )
  {
    var str = '';

    if ( this.lceEntries.length == 0 )
    {
      str = 'No entries in cache!';
    }

    var count = 0;
    for ( var x in this.lceEntries )
    {
      var entry = this.lceEntries[x];
      str += count + ':';
      str += entry.toString();
      str += '<br/>\n';

      count++;
    }

    htmlE.innerHTML = str;
  }
});
