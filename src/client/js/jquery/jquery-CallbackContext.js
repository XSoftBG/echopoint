jQuery.extend(
{
  context: function (context)
  {
    var co =
    {
      callback: function (method)
      {
        if (typeof method == 'string') method = context[method];
        var cb = function () { method.apply(context, arguments); }
        return cb;
      }
    };
    return co;
  }
});