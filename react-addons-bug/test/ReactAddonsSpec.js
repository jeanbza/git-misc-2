import React from 'react/addons';
import sinon from 'sinon';

var ActionLink = React.createClass({
  render: function() {
    return <a href="some url" onClick={this.props.onClick}>Some text</a>;
  }
});

// Test supplied by @TGOlson
describe("test supplied by @TGOlson - barebones react/addons", function() {
  it("should invoke the supplied function when clicked", function() {
    const onClick = sinon.spy();
    const actionLink = <ActionLink onClick={onClick}/>;

    const div = document.createElement('div');
    React.render(actionLink, div);
    const element = div.children[0];

    React.addons.TestUtils.Simulate.click(element);
    expect(onClick.called).toEqual(true);
  });
});
