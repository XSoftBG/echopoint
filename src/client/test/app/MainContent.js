/**
 * The main content pane in which the application components are laid out.
 * Uses a split pane to display the {@link ComponentList} and the various
 * test components.
 *
 * @author Rakesh 2008-06-26
 * @version $Id: MainContent.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.test.MainContent = Core.extend( Echo.ContentPane,
{
  /** The component list component that displays the component test controls. */
  _componentList: null,

  /**
   * The container in which the component being tested and any supporting
   * components are displayed.
   */
  _testArea: null,

  $construct: function()
  {
    this._testArea = new Echo.Column();
    this._componentList = new echopoint.test.ComponentList();
    Echo.ContentPane.call( this,
    {
      children: [ this._createSplitPane() ]
    });
  },

  /** Create the split pane used to layout the interface. */
  _createSplitPane: function()
  {
    return new Echo.SplitPane(
    {
      styleName: "DefaultResizableLarge",
      separatorPosition: 300,
      children: [ this._componentList, this._testArea ]
    });
  },

  /** Return the {@link #_componentList}. */
  getComponentList: function() { return this._componentList; },

  /** Return the {@link #_testArea}. */
  getTestArea: function() { return this._testArea; }
});