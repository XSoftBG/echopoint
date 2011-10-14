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
/**
 * The core library for the echopoint components.  Sets up the core namespaces
 * and constants for use.
 *
 * @author Rakesh 2008-06-20
 * @version $Id: Echopoint.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */

/**
 * The echopoint namespace to contain all components and rendering peers.
 */
echopoint =
{
  /**
   * Maintains a unique id for the echopoint namespace.
   *
   * @type Number
   */
  uniqueId: 20080623
};

/**
 * The namespace for holding internal implementation classes.  Used typically
 * to hold abstract super-classes for components exposed by the library.
 */
echopoint.internal =
{
  /**
   * Maintains a unique id for the echopoint.internal namespace.
   *
   * @type Number
   */
  uniqueId: 20080627
};

/**
 * The namespace for holding constants used by the framework.  Constants
 * are defined for the various component names to ensure proper usage
 * in registering the components and their peers.
 */
echopoint.constants =
{
  /**
   * Maintains a unique id for the echopoint.constants namespace.
   *
   * @type Number
   */
  uniqueId: 20080624
};

/** The namespace for client-side model objects. */
echopoint.model =
{
   /**
   * Maintains a unique id for the echopoint.model namespace.
   *
   * @type Number
   */
  uniqueId: 20080720,

  /**
   * Copyright 2007 Tim Down.
   *
   * Licensed under the Apache License, Version 2.0 (the "License");
   * you may not use this file except in compliance with the License.
   * You may obtain a copy of the License at
   *
   *      http://www.apache.org/licenses/LICENSE-2.0
   *
   * Unless required by applicable law or agreed to in writing, software
   * distributed under the License is distributed on an "AS IS" BASIS,
   * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   * See the License for the specific language governing permissions and
   * limitations under the License.
   *
   * A faithful JavaScript implementation of Java's SimpleDateFormat's format
   * method. All pattern layouts present in the Java implementation are
   * implemented here except for z, the text version of the date's time zone.
   *
   * Thanks to Ash Searle (http://hexmen.com/blog/) for his fix to my
   * misinterpretation of pattern letters h and k.
   *
   * See the official Sun documentation for the Java version:
   * http://java.sun.com/j2se/1.5.0/docs/api/java/text/SimpleDateFormat.html
   *
   * Author: Tim Down <tim@timdown.co.uk>
   * Last modified: 6/2/2007
   * Website: http://www.timdown.co.uk/code/simpledateformat.php
   */
  isUndefined: function( obj )
  {
    return typeof obj == "undefined";
  },

  Date: Core.extend( Date,
  {
    $static:
    {
      ONE_DAY: 24 * 60 * 60 * 1000,
      ONE_WEEK: 7 * this.ONE_DAY,
      DEFAULT_MINIMAL_DAYS_IN_FIRST_WEEK: 1,

      newDateAtMidnight: function( year, month, day )
      {
        var d = new Date( year, month, day, 0, 0, 0 );
        d.setMilliseconds( 0 );
        return new echopoint.model.Date( d );
      }
    },

    $construct: function( year, month, day, hour, minute, second, millis )
    {
      Date.call( year, month, day, hour, minute, second, millis );
    },

    getDifference: function( date )
    {
      return this.getTime() - date.getTime();
    },

    isBefore: function( d )
    {
      return this.getTime() < d.getTime();
    },

    getUTCTime: function()
    {
      return Date.UTC( this.getFullYear(), this.getMonth(), this.getDate(),
          this.getHours(), this.getMinutes(), this.getSeconds(),
          this.getMilliseconds() );
    },

    getTimeSince: function( d )
    {
      return this.getUTCTime() - d.getUTCTime();
    },

    getPreviousSunday: function()
    {
      // Using midday avoids any possibility of DST messing things up
      var midday = new echopoint.model.Date( this.getFullYear(),
          this.getMonth(), this.getDate(), 12, 0, 0 );
      var previousSunday = new echopoint.model.Date(
          midday.getTime() - this.getDay() * echopoint.model.Date.ONE_DAY );

      return echopoint.model.Date.newDateAtMidnight( previousSunday.getFullYear(),
          previousSunday.getMonth(), previousSunday.getDate() );
    },

    getWeekInYear: function( minimalDaysInFirstWeek )
    {
      if ( echopoint.model.isUndefined( this.minimalDaysInFirstWeek ) )
      {
        minimalDaysInFirstWeek =
            echopoint.model.Date.DEFAULT_MINIMAL_DAYS_IN_FIRST_WEEK;
      }

      var previousSunday = this.getPreviousSunday();
      var startOfYear = echopoint.model.Date.newDateAtMidnight(
          this.getFullYear(), 0, 1 );
      var numberOfSundays = previousSunday.isBefore(startOfYear) ? 0 :
                            1 + Math.floor(
                                previousSunday.getTimeSince( startOfYear ) /
                                echopoint.model.Date.ONE_WEEK );
      var numberOfDaysInFirstWeek = 7 - startOfYear.getDay();
      var weekInYear = numberOfSundays;

      if ( numberOfDaysInFirstWeek < minimalDaysInFirstWeek )
      {
        weekInYear--;
      }

      return weekInYear;
    },

    getWeekInMonth: function( minimalDaysInFirstWeek )
    {
      if ( echopoint.model.isUndefined( this.minimalDaysInFirstWeek ) )
      {
        minimalDaysInFirstWeek =
            echopoint.model.Date.DEFAULT_MINIMAL_DAYS_IN_FIRST_WEEK;
      }

      var previousSunday = this.getPreviousSunday();
      var startOfMonth = echopoint.model.Date.newDateAtMidnight(
          this.getFullYear(), this.getMonth(), 1 );
      var numberOfSundays = previousSunday.isBefore( startOfMonth ) ? 0 :
                            1 + Math.floor(
                                ( previousSunday.getTimeSince( startOfMonth ) )
                                    / echopoint.model.Date.ONE_WEEK );
      var numberOfDaysInFirstWeek = 7 - startOfMonth.getDay();
      var weekInMonth = numberOfSundays;

      if ( numberOfDaysInFirstWeek >= minimalDaysInFirstWeek )
      {
        weekInMonth++;
      }

      return weekInMonth;
    },

    getDayInYear: function()
    {
      var startOfYear = echopoint.model.Date.newDateAtMidnight(
          this.getFullYear(), 0, 1 );
      return 1 + Math.floor( this.getTimeSince( startOfYear ) /
                             echopoint.model.Date.ONE_DAY );
    }
  }),

  SimpleDateFormat: Core.extend(
  {
    $static:
    {
      monthNames: [ "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December" ],
      dayNames: [ "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday" ],

      TEXT2: 0,
      TEXT3: 1,
      NUMBER: 2,
      YEAR: 3,
      MONTH: 4,
      TIMEZONE: 5,

      types:
      {
        G : this.TEXT2,
        y : this.YEAR,
        M : this.MONTH,
        w : this.NUMBER,
        W : this.NUMBER,
        D : this.NUMBER,
        d : this.NUMBER,
        F : this.NUMBER,
        E : this.TEXT3,
        a : this.TEXT2,
        H : this.NUMBER,
        k : this.NUMBER,
        K : this.NUMBER,
        h : this.NUMBER,
        m : this.NUMBER,
        s : this.NUMBER,
        S : this.NUMBER,
        Z : this.TIMEZONE
      }
    },

    /** The date format string. */
    formatString: null,

    /** The regular expression used to format the date. */
    regex: /('[^']*')|(G+|y+|M+|w+|W+|D+|d+|F+|E+|a+|H+|k+|K+|h+|m+|s+|S+|Z+)|([a-zA-Z]+)|([^a-zA-Z']+)/,

    minimalDaysInFirstWeek: Date.DEFAULT_MINIMAL_DAYS_IN_FIRST_WEEK,

    $construct: function( formatString )
    {
      this.formatString = formatString;
    },

    /**
     * Sets the minimum number of days in a week in order for that week to
     * be considered as belonging to a particular month or year
     */
    setMinimalDaysInFirstWeek: function( days )
    {
      this.minimalDaysInFirstWeek = days;
    },

    getMinimalDaysInFirstWeek: function()
    {
      return echopoint.model.isUndefined( this.minimalDaysInFirstWeek ) ?
             echopoint.model.Date.DEFAULT_MINIMAL_DAYS_IN_FIRST_WEEK :
             this.minimalDaysInFirstWeek;
    },

    /** Format the specified date and return the formaatted string. */
    format: function( indate )
    {
      var date = new echopoint.model.Date( indate.getFullYear(),
          indate.getMonth(), indate.getDay(), indate.getHours(),
          indate.getMinutes(), indate.getSeconds(),
          indate.getMilliseconds() );
      var formattedString = "";
      var result;

      var padWithZeroes = function( str, len )
      {
        while ( str.length < len )
        {
          str = "0" + str;
        }

        return str;
      };

      var formatText = function( data, numberOfLetters, minLength )
      {
        return ( numberOfLetters >= 4 ) ? data :
               data.substr( 0, Math.max( minLength, numberOfLetters ) );
      };

      var formatNumber = function( data, numberOfLetters )
      {
        var dataString = "" + data;
        // Pad with 0s as necessary
        return padWithZeroes(dataString, numberOfLetters);
      };

      var searchString = this.formatString;
      while ( ( result = this.regex.exec( searchString ) ) )
      {
        //var matchedString = result[0];
        var quotedString = result[1];
        var patternLetters = result[2];
        var otherLetters = result[3];
        var otherCharacters = result[4];

        // If the pattern matched is quoted string, output the text between the quotes
        if ( quotedString )
        {
          if ( quotedString == "''" )
          {
            formattedString += "'";
          }
          else
          {
            formattedString += quotedString.substring(1, quotedString.length - 1);
          }
        }
        else if ( otherLetters )
        {
          // Swallow non-pattern letters by doing nothing here
        }
        else if ( otherCharacters )
          {
            // Simply output other characters
            formattedString += otherCharacters;
          }
          else if ( patternLetters )
            {
              // Replace pattern letters
              var patternLetter = patternLetters.charAt(0);
              var numberOfLetters = patternLetters.length;
              var rawData = "";
              switch ( patternLetter )
                  {
                case "G":
                  rawData = "AD";
                  break;
                case "y":
                  rawData = date.getFullYear();
                  break;
                case "M":
                  rawData = date.getMonth();
                  break;
                case "w":
                  rawData = date.getWeekInYear(this.getMinimalDaysInFirstWeek());
                  break;
                case "W":
                  rawData = date.getWeekInMonth(this.getMinimalDaysInFirstWeek());
                  break;
                case "D":
                  rawData = date.getDayInYear();
                  break;
                case "d":
                  rawData = date.getDate();
                  break;
                case "F":
                  rawData = 1 + Math.floor((date.getDate() - 1) / 7);
                  break;
                case "E":
                  rawData = echopoint.model.SimpleDateFormat.dayNames[date.getDay()];
                  break;
                case "a":
                  rawData = (date.getHours() >= 12) ? "PM" : "AM";
                  break;
                case "H":
                  rawData = date.getHours();
                  break;
                case "k":
                  rawData = date.getHours() || 24;
                  break;
                case "K":
                  rawData = date.getHours() % 12;
                  break;
                case "h":
                  rawData = (date.getHours() % 12) || 12;
                  break;
                case "m":
                  rawData = date.getMinutes();
                  break;
                case "s":
                  rawData = date.getSeconds();
                  break;
                case "S":
                  rawData = date.getMilliseconds();
                  break;
                case "Z":
                  // This is returns the number of minutes since GMT was this time.
                  rawData = date.getTimezoneOffset();
                  break;
              }
              // Format the raw data depending on the type
              switch ( echopoint.model.SimpleDateFormat.types[patternLetter] )
                  {
                case echopoint.model.SimpleDateFormat.TEXT2:
                  formattedString += formatText(rawData, numberOfLetters, 2);
                  break;
                case echopoint.model.SimpleDateFormat.TEXT3:
                  formattedString += formatText(rawData, numberOfLetters, 3);
                  break;
                case echopoint.model.SimpleDateFormat.NUMBER:
                  formattedString += formatNumber(rawData, numberOfLetters);
                  break;
                case echopoint.model.SimpleDateFormat.YEAR:
                  if ( numberOfLetters <= 3 )
                  {
                    // Output a 2-digit year
                    var dataString = "" + rawData;
                    formattedString += dataString.substr(2, 2);
                  }
                  else
                  {
                    formattedString += formatNumber(rawData, numberOfLetters);
                  }
                  break;
                case echopoint.model.SimpleDateFormat.MONTH:
                  if ( numberOfLetters >= 3 )
                  {
                    formattedString += formatText(
                        echopoint.model.SimpleDateFormat.monthNames[rawData],
                        numberOfLetters, numberOfLetters);
                  }
                  else
                  {
                    // NB. Months returned by getMonth are zero-based
                    formattedString += formatNumber(rawData + 1, numberOfLetters);
                  }
                  break;
                case echopoint.model.SimpleDateFormat.TIMEZONE:
                  var isPositive = (rawData > 0);
                  // The following line looks like a mistake but isn't
                  // because of the way getTimezoneOffset measures.
                  var prefix = isPositive ? "-" : "+";
                  var absData = Math.abs(rawData);

                  // Hours
                  var hours = "" + Math.floor(absData / 60);
                  hours = padWithZeroes(hours, 2);
                  // Minutes
                  var minutes = "" + (absData % 60);
                  minutes = padWithZeroes(minutes, 2);

                  formattedString += prefix + hours + minutes;
                  break;
              }
            }
        searchString = searchString.substr(result.index + result[0].length);
      }

      return formattedString;
    }
  })
};

/** The root namespace for Google API related components. */
echopoint.google =
{
  /**
   * Maintains a unique id for the echopoint.google.chart namespace.
   *
   * @type Number
   */
  uniqueId: 20080808
};

/** The namespace for Google API related components. */
echopoint.google.chart =
{
  /**
   * Maintains a unique id for the echopoint.google.chart namespace.
   *
   * @type Number
   */
  uniqueId: 20080829
};

/** The namespace for internal comopnents of Google API related components. */
echopoint.google.chart.internal =
{
  /**
   * Maintains a unique id for the echopoint.google.chart.internal namespace.
   *
   * @type Number
   */
  uniqueId: 200808091
};

/** The namespace for model objects of Google API related components. */
echopoint.google.chart.model =
{
  /**
   * Maintains a unique id for the echopoint.google.chart.model namespace.
   *
   * @type Number
   */
  uniqueId: 200808092
};

/** The namespace for tucana components ported to Echo3. */
echopoint.tucana =
{
  /**
   * Maintains a unique id for the echopoint.tucana namespace.
   *
   * @type Number
   */
  uniqueId: 20081030
};

