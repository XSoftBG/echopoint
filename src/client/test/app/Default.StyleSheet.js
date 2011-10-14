echopoint.test.TestApp.StyleSheet = new Echo.StyleSheet(
{
  "RowStrut":
  {
    "echopoint.Strut":
    {
      border: "1px outset #709bcd",
      width: "50px",
      height: "20px"
    }
  },
  "ColumnStrut":
  {
    "echopoint.Strut":
    {
      border: "1px outset #709bcd",
      height: "100px",
      width: "100px"
    }
  },
  "SimpleChart":
  {
    "echopoint.google.chart.BarChart":
    {
      axisType: "x,y,t,r",
      border: "2px groove #cfdfff",
      fill: "bg,s,efefef|c,lg,45,ffffff,0,76a4fb,0.75",
      foreground: "ff0000",
      font: { size: "10" },
      gridLines: "10,20,5,2",
      legendPosition: "l",
      width: "400",
      height: "400",
      insets: 10,
      size: "10,5,15"
    },
    "echopoint.google.chart.LineChart":
    {
      axisType: "x,y",
      axisStyles: "0,00ff33,13,1|1,0033ff,13,-1",
      border: "2px groove #cfdfff",
      fill: "bg,s,efefef|c,s,00000080",
      font: { size: "12" },
      foreground: "ff0000",
      gridLines: "10,10,1,0",
      width: "600",
      height: "400",
      insets: 10
    },
    "echopoint.google.chart.Sparkline":
    {
      axisType: "x,y",
      axisStyles: "0,00ff33,13,1|1,0033ff,13,-1",
      border: "2px groove #cfdfff",
      fill: "bg,s,efefef|c,s,00000080",
      font: { size: "12" },
      foreground: "ff0000",
      gridLines: "10,10,1,0",
      width: "600",
      height: "400",
      insets: 10
    },
    "echopoint.google.chart.PieChart":
    {
      border: "2px groove #cfdfff",
      fill: "bg,s,efefef|c,s,00000080",
      font: { size: "12" },
      foreground: "ff0000",
      width: "600",
      height: "400",
      insets: 10
    },
    "echopoint.google.chart.VennDiagram":
    {
      border: "2px groove #cfdfff",
      fill: "bg,s,efefef|c,s,00000080",
      font: { size: "12" },
      foreground: "ff0000",
      width: "600",
      height: "400",
      insets: 10
    },
    "echopoint.google.chart.Map":
    {
      border: "2px groove #cfdfff",
      fill: "bg,s,efefef|c,lg,45,ffffff,0,76a4fb,0.75",
      foreground: "ff0000",
      font: { size: "10" },
      insets: 10
    },
    "echopoint.google.chart.Meter":
    {
      border: "2px groove #cfdfff",
      fill: "bg,s,efefef|c,lg,45,ffffff,0,76a4fb,0.75",
      foreground: "ff0000",
      font: { size: "10" },
      insets: 10,
      width: "400",
      height: "300",
      label: "70 %"
    },
    "echopoint.google.chart.QRCode":
    {
      border: "2px groove #cfdfff",
      fill: "bg,s,efefef|c,lg,45,ffffff,0,76a4fb,0.75",
      foreground: "ff0000",
      font: { size: "10" },
      insets: 10,
      width: "400",
      height: "300",
      encoding: "ISO-8859-1"
    }
  },
  "ComplexChart":
  {
    "echopoint.google.chart.LineChart":
    {
      axisType: "x,y,t,r",
      border: "2px groove #cfdfff",
      fill: "bg,s,efefef|c,lg,45,ffffff,0,76a4fb,0.75",
      foreground: "ff0000",
      font: { size: "10" },
      gridLines: "10,20,5,2",
      legendPosition: "l",
      width: "600",
      height: "400",
      insets: 10
    },
    "echopoint.google.chart.ScatterPlot":
    {
      axisType: "x,y,t,r",
      border: "2px groove #cfdfff",
      foreground: "ff0000",
      font: { size: "10" },
      gridLines: "10,20,5,2",
      legendPosition: "l",
      width: "600",
      height: "400",
      insets: 10
    },
    "echopoint.google.chart.Sparkline":
    {
      axisType: "x,y,t,r",
      border: "2px groove #cfdfff",
      fill: "bg,s,efefef|c,lg,45,ffffff,0,76a4fb,0.75",
      foreground: "ff0000",
      font: { size: "10" },
      gridLines: "10,20,5,2",
      legendPosition: "l",
      width: "600",
      height: "400",
      insets: 10
    },
    "echopoint.google.chart.RadarChart":
    {
      axisType: "x",
      border: "2px groove #cfdfff",
      fill: "bg,s,efefef|c,lg,45,ffffff,0,76a4fb,0.75",
      foreground: "ff0000",
      font: { size: "10" },
      gridLines: "10,20,5,2",
      legendPosition: "l",
      width: "600",
      height: "400",
      insets: 10
    },
    "echopoint.google.chart.Map":
    {
      border: "2px groove #cfdfff",
      fill: "bg,s,efefef|c,lg,45,ffffff,0,76a4fb,0.75",
      foreground: "ff0000",
      font: { size: "10" },
      insets: 10,
      region: "africa",
      codes: "MGKETN"
    }
  },
  "ControlPane": {
    "Row": {
      layoutData: {
        backgroundImage: "image/ControlPaneFill.png",
        overflow: Echo.SplitPane.OVERFLOW_HIDDEN,
        insets: "2px 10px"
      },
      cellSpacing: 20,
      insets: "2px 10px"
    }
  },
  "ControlPane.Button": {
    "Button": {
      lineWrap: false,
      foreground: "#000000",
      rolloverForeground: "#6f0f0f",
      rolloverEnabled: true,
      insets: "0px 8px"
    }
  },
  "ControlPane.Container.Top": {
    "SplitPane": {
      orientation: Echo.SplitPane.ORIENTATION_VERTICAL_TOP_BOTTOM,
      separatorPosition: "30px"
    }
  },
  "ControlPane.Container.Bottom": {
    "SplitPane": {
      orientation: Echo.SplitPane.ORIENTATION_VERTICAL_BOTTOM_TOP,
      separatorPosition: "30px"
    }
  },
  "Default": {
    "AbstractButton": {
      border: "1px outset #709bcd",
      foreground: "#000000",
      backgroundImage: {
        url: "image/InputFieldBackground.png",
        y: "50%"
      },
      pressedBackgroundImage: {
        url: "image/InputFieldBackgroundPressed.png",
        y: "50%"
      },
      pressedBorder: "1px inset #709bcd",
      rolloverBackgroundImage: {
        url: "image/InputFieldBackgroundHighlight.png",
        y: "50%"
      },
      rolloverBorder: "1px outset #bcd6f4",
      pressedEnabled: true,
      rolloverEnabled: true,
      insets: "1px 4px",
      disabledForeground: "#93bed5"
    },
    "AbstractListComponent": {
      border: "2px groove #cfdfff",
      background: "#cfdfff"
    },
    "Extras.AccordionPane": {
      tabBorder: "1px outset #272727",
      tabForeground: "#ffffff",
      tabBackground: "#514f58",
      tabBackgroundImage: "image/TabBackground.png",
      tabRolloverEnabled: true,
      tabRolloverBackground: "#86899a",
      tabRolloverBackgroundImage: "image/TabBackgroundRollover.png"
    },
    "Extras.MenuBarPane": {
      animationTime: 150,
      border: "0px solid #000000",
      backgroundImage: "image/PulldownMenuBackground.png",
      foreground: "#ffffff",
      menuBackground: "#000000",
      menuOpacity: 92,
      menuBackgroundImage: "image/GreyMenuBackground.png",
      menuBorder: {
        bottomy: "1px solid #3f3f3f",
        bottomx: "1px solid #3f3f3f",
        topx: "1px solid #1f1f1f",
        topy: "1px solid #1f1f1f"
      },
      selectionBackground: "#fffac1",
      selectionBackgroundImage: {
        url:"image/BeigeLightedBackground.png",
        x: "50%",
        y: "50%"
      },
      selectionForeground: "#000000"
    },
    "Extras.RichTextArea": {
      controlPaneSplitPaneStyleName: "ControlPane.Container.Bottom",
      controlPaneRowStyleName: "ControlPane",
      controlPaneButtonStyleName: "ControlPane.Button",
      menuStyleName: "RichTextArea.Menu",
      toolbarButtonStyleName: "RichTextArea.Toolbar",
      windowPaneStyleName: "GlassBlue2"
    },
    "TextComponent": {
      background: "#cfdfff",
      border: "2px groove #cfdfff",
      foreground: "#000000",
      disabledForeground: "#999999",
      backgroundImage: {
        url: "image/InputFieldBackground.png",
        repeat: "repeat-x",
        y: "50%"
      }
    },
    "WindowPane": {
      ieAlphaRenderBorder: true,
      titleForeground: "#ffffff",
      titleBackground: "#2f2f4f",
      titleInsets: "5px 10px",
      titleBackgroundImage: {
        url: "image/window/simple/Header.png",
        repeat: "repeat-x",
        y: "100%"
      },
      border: {
        contentInsets: "8px 14px 14px 8px",
        borderInsets: "17px 23px 23px 17px",
        topLeft: "image/window/simple/BorderTopLeft.png",
        bottomy: "image/window/simple/BorderTop.png",
        topRight: "image/window/simple/BorderTopRight.png",
        bottomx: "image/window/simple/BorderLeft.png",
        topx: "image/window/simple/BorderRight.png",
        bottomLeft: "image/window/simple/BorderBottomLeft.png",
        topy: "image/window/simple/BorderBottom.png",
        bottomRight: "image/window/simple/BorderBottomRight.png"
      }
    },
    "echopoint.Anchor":
    {
      foreground: "#2f2f4f",
      font: {
        bold: true,
        size: "10"
      },
      target: "_blank",
      text: "A HTML Anchor Tag",
      toolTipText: "Click the link",
      uri: "https://echopoint.dev.java.net/"
    },
    "echopoint.BorderLayout":
    {
      border: "2px groove #cfdfff",
      width: "500px"
    },
    "echopoint.DirectHtml":
    {
      background: "#cfdfff",
      border: "2px groove #cfdfff",
      backgroundImage:
      {
        url: "image/InputFieldBackground.png",
        repeat: "repeat-x",
        y: "50%"
      },
      width: "500px",
      height: "100px"
    },
    "echopoint.HtmlLabel":
    {
      background: "#cfdfff",
      border: "2px groove #cfdfff",
      backgroundImage:
      {
        url: "image/InputFieldBackground.png",
        repeat: "repeat-x",
        y: "100%"
      }
    },
    "echopoint.HttpPane":
    {
      border: "2px groove #cfdfff",
      backgroundImage:
      {
        url: "image/LoginBackground.png",
        repeat: "repeat"
      },
      insets: 10
    },
    "echopoint.ImageIcon":
    {
      border: "2px groove #cfdfff",
      url: "image/imagemap.gif",
      width: "500px",
      height: "300px",
      toolTipText: "Image Map",
      cursor: "crosshair"
    },
    "echopoint.InfoWindow":
    {
      textForeground: "#2f2f4f",
      textBackground: "#cfdfff",
      textFont: {
        bold: true,
        size: "12"
      },
      textInsets: "3px 8px",
      otherTextForeground: "#000000",
      otherTextFont: {
        bold: false,
        size: "11"
      },
      otherTextInsets: "2px 5px",
      titleAlignment: "left",
      titleBackground: "#9f9f9f",
      titleForeground: "white",
      titleFont: {
        bold: true,
        size: "12"
      },
      alignment: "center",
      insets: 10,
      font: { size: "11" },
      background: "#f9f9f9",
      foreground: "#a10202",
      width: "250px"
    },
    "echopoint.ProgressBar":
    {
      background: "#a1a1a1",
      foreground: "#ffffff",
      border: "2px inset #3d3d3d",
      barBackground: "#1a428a",
      insets: 1,
      height: "25px",
      width: "500px"
    },
    "echopoint.PushButton":
    {
      text: "Click Me",
      toolTipText: "Click me to test"
    },
    "echopoint.TagCloud":
    {
      border: "2px inset #3d3d3d",
      insets: 1,
      rolloverBackground: "#a1a1a1",
      rolloverForeground: "#c1c1c1",
      rolloverEnabled: true,
      width: "500px"
    }
  },
  "DefaultResizableLarge": {
    "SplitPane" : {
      separatorHeight: 12,
      separatorWidth: 12,
      resizable: true,
      separatorHorizontalImage: {
        url: "image/SplitPaneHorizontalSeparatorLarge.png",
        y: "50%"
      },
      separatorVerticalImage: {
        url: "image/SplitPaneVerticalSeparatorLarge.png",
        x: "50%"
      }
    }
  },
  "LaunchPanel": {
    "Button": {
      alignment: "center",
      background: "#1f1f1f",
      textAlignment: "center",
      textPosition: "topy",
      rolloverEnabled: true,
      rolloverBackground: "#5c5c5c",
      foreground: "#ffffff",
      font: { size: "9pt" },
      iconTextMargin: 2,
      insets: 10,
      pressedBackground: "#4d4d4d",
      pressedEnabled: true,
      border: "2px groove #3d3d3d",
      pressedBorder: "2px inset #3d3d3d"
    },
    "Column": {
      cellSpacing: 8
    }
  },
  "LaunchPanel.Selected": {
    "Button": {
      alignment: "center",
      textAlignment: "center",
      textPosition: "topy",
      foreground: "#ffffff",
      backgroundImage: "image/DemoSelectButtonSelectedBackground.png",
      font: { size: "9pt" },
      iconTextMargin: 2,
      insets: 10,
      border: "2px groove #3d3d3d"
    }
  },
  "GlassBlue": {
    "WindowPane": {
      ieAlphaRenderBorder: true,
      titleFont: {
        size: "10pt",
        bold: true,
        italic: true
      },
      titleForeground: "#ffffff",
      titleInsets: "5px 10px",
      titleBackgroundImage: {
        url: "image/window/glassblue/Header.png",
        repeat: "repeat-x",
        y: "100%"
      },
      border: {
        contentInsets: "6px 15px 15px 12px",
        borderInsets: "34px 20px 20px 20px",
        topLeft: "image/window/glassblue/BorderTopLeft.png",
        bottomy: "image/window/glassblue/BorderTop.png",
        topRight: "image/window/glassblue/BorderTopRight.png",
        bottomx: "image/window/glassblue/BorderLeft.png",
        topx: "image/window/glassblue/BorderRight.png",
        bottomLeft: "image/window/glassblue/BorderBottomLeft.png",
        topy: "image/window/glassblue/BorderBottom.png",
        bottomRight: "image/window/glassblue/BorderBottomRight.png"
      }
    }
  },
  "GlassBlue2": {
    "WindowPane": {
      ieAlphaRenderBorder: true,
      titleFont: {
        size: "10pt",
        bold: true,
        italic: true
      },
      titleForeground: "#ffffff",
      titleInsets: "5px 10px",
      titleBackgroundImage: {
        url: "image/window/glassblue2/Header.png",
        repeat: "repeat-x",
        y: "100%"
      },
      border: {
        contentInsets: "6px 15px 15px 12px",
        borderInsets: "34px 20px 20px 20px",
        topLeft: "image/window/glassblue2/BorderTopLeft.png",
        bottomy: "image/window/glassblue2/BorderTop.png",
        topRight: "image/window/glassblue2/BorderTopRight.png",
        bottomx: "image/window/glassblue2/BorderLeft.png",
        topx: "image/window/glassblue2/BorderRight.png",
        bottomLeft: "image/window/glassblue2/BorderBottomLeft.png",
        topy: "image/window/glassblue2/BorderBottom.png",
        bottomRight: "image/window/glassblue2/BorderBottomRight.png"
      }
    }
  },
  "Layout.Bordered": {
    "Grid": {
      width: "100%",
      insets: "3px 8px",
      background: "#ffffff",
      border: "2px groove #7ea4d3"
    }
  },
  "Junior": {
    "Extras.ColorSelect": {
      hueWidth: 10,
      saturationHeight: 60,
      valueWidth: 60
    }
  },
  "Photo.Countryside": {
    "ContentPane": {
      backgroundImage: {
        url: "image/bgpictures/Countryside.jpg",
        x: -1,
        y: "100%"
      }
    }
  },
  "Photo.Coral": {
    "ContentPane": {
      backgroundImage: {
        url: "image/bgpictures/Coral.jpg",
        x: 300,
        y: "50%"
      }
    }
  },
  "Photo.EarthEast": {
    "ContentPane": {
      background: "#000000",
      backgroundImage: {
        url: "image/bgpictures/EarthEast.jpg",
        y: "100%",
        repeat: "no-repeat"
      }
    }
  },
  "Photo.EarthWest": {
    "ContentPane": {
      background: "#000000",
      backgroundImage: {
        url: "image/bgpictures/EarthWest.jpg",
        y: "100%",
        repeat: "no-repeat"
      }
    }
  },
  "Photo.Fern": {
    "ContentPane": {
      backgroundImage: {
        url: "image/bgpictures/Fern.jpg",
        x: "50%",
        y: "50%"
      }
    }
  },
  "Photo.Leaf": {
    "ContentPane": {
      backgroundImage: {
        url: "image/bgpictures/Leaf.jpg",
        x: "50%",
        y: "50%"
      }
    }
  },
  "Photo.Moonlight": {
    "ContentPane": {
      backgroundImage: {
        url: "image/bgpictures/Moonlight.jpg",
        x: -1,
        y: -1
      }
    }
  },
  "Photo.Poinsettia": {
    "ContentPane": {
      backgroundImage: {
        url: "image/bgpictures/Poinsettia.jpg",
        x: -1,
        y: "70%"
      }
    }
  },
  "Photo.Winter": {
    "ContentPane": {
      backgroundImage: {
        url: "image/bgpictures/Winter.jpg",
        x: -1,
        y: -1
      }
    }
  },
  "PhotoAlbum": {
    "Button": {
      insets: 3,
      foreground: "#ffffff",
      rolloverEnabled: true,
      rolloverBackground: "#000000",
      rolloverForeground: "#fffed0",
      alignment: "center",
      textAlignment: "center",
      textPosition: "topy",
      iconTextMargin: 1,
      layoutData: {
        alignment: "center"
      }
    }
  },
  "PreferencesColumn": {
    "Column": {
      border: {
        bottomx: "1px solid #afafaf",
        bottomy: "1px solid #afafaf",
        topx: "1px solid #dfdfdf",
        topy: "1px solid #dfdfdf"
      },
      cellSpacing: 8,
      insets: "8px 20px"
    }
  },
  "PreferencesTitle": {
    "Label": {
      foreground: "#2f2faf",
      font: { bold: true }
    }
  },
  "RichTextArea.Menu": {
    "Extras.MenuBarPane": {
      backgroundImage: "image/BlueLineBackground.png",
      menuBackgroundIamge: "image/LightBlueLineBackground.png",
      selectionBackgroundImage: "image/ShadowBackgroundDarkBlue2.png"
    }
  },
  "RichTextArea.Toolbar": {
    "Button": {
      background: "#cfcfdf",
      foreground: "#000000",
      border: "1px outset #cfcfdf",
      rolloverEnabled: true,
      rolloverBackground: "#efefff",
      rolloverBorder: "1px outset #efefff",
      pressedEnabled: true,
      pressedBackground: "#afafbf",
      pressedBorder: "1px inset #afafbf",
      insets: "1px 3px"
    }
  },
  "TransGreen": {
    "WindowPane": {
      ieAlphaRenderBorder: true,
      titleFont: {
        size: "10pt",
        bold: true,
        italic: true
      },
      titleForeground: "#ffffff",
      titleInsets: "5px 10px",
      titleBackgroundImage: {
        url: "image/window/transgreen/Header.png",
        repeat: "repeat-x",
        y: "100%"
      },
      border: {
        contentInsets: "6px 15px 15px 12px",
        borderInsets: "34px 20px 20px 20px",
        topLeft: "image/window/transgreen/BorderTopLeft.png",
        bottomy: "image/window/transgreen/BorderTop.png",
        topRight: "image/window/transgreen/BorderTopRight.png",
        bottomx: "image/window/transgreen/BorderLeft.png",
        topx: "image/window/transgreen/BorderRight.png",
        bottomLeft: "image/window/transgreen/BorderBottomLeft.png",
        topy: "image/window/transgreen/BorderBottom.png",
        bottomRight: "image/window/transgreen/BorderBottomRight.png"
      }
    }
  }
});
