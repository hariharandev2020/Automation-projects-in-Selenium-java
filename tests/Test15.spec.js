import { Selector,ClientFunction } from 'testcafe';
import page from './method.po';

fixture `Test JQuery UI page`
.page `https://jqueryui.com/`

.beforeEach(async t => {
    await t.maximizeWindow()
    page.browserscroll();
    const tooltip  = Selector('a').withExactText('Tooltip');
    await t.click(tooltip);
});
test("Tooltip text test", async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const link1 = Selector('a').withExactText('Tooltips');
    const p1    = Selector('p').nth(0);
    const link2 = Selector('a').withExactText('ThemeRoller');
    const p2    = Selector('p').nth(1);
    const p3    = Selector('p').nth(2);
    const p4    = Selector('p').nth(4);
    const label = Selector('label[for = age]');
    const input = Selector('#age');
    await t.expect(await link1.innerText).eql('Tooltips');
    await t.expect(await p1.innerText).contains('can be attached to any element. When you hover the element with your mouse, the title attribute is displayed in a little box next to the element, just like a native tooltip.');
    await t.expect(await link2.innerText).contains("ThemeRoller");
    await t.expect(await p2.innerText).contains("But as it's not a native tooltip, it can be styled. Any themes built with ThemeRoller will also style tooltips accordingly.");
    await t.expect(await p3.innerText).contains("Tooltips are also useful for form elements, to show some additional information in the context of each field.");
    await t.expect(await label.innerText).contains("Your age:");
    await t.expect(await input.exists).ok();
    await t.click(input);
    await t.typeText(input,'21');
    await t.expect(await input.value).eql("21");
    await t.expect(await p4.innerText).contains("Hover the field to see the tooltip.");
})
test('A tag color test', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const link1 = Selector('a').withExactText('Tooltips');
    const link2 = Selector('a').withExactText('ThemeRoller');
    await t.expect(await link1.getStyleProperty('color')).contains("rgb(0, 0, 238)");
    await t.expect(await link2.getStyleProperty('color')).contains("rgb(0, 0, 238)");
})
test('Tooltip title check', async t=> { 
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const link1 = Selector('a').withExactText('Tooltips');
    const link2 = Selector('a').withExactText('ThemeRoller');
    const input = Selector('#age');
    await t.expect(await link1.getAttribute('title')).contains("That's what this widget is");
    await t.expect(await link2.getAttribute('title')).contains("ThemeRoller: jQuery UI's theme builder application");
    await t.expect(await input.getAttribute('title')).contains("We ask for your age only for statistical purposes.");
})
test('Tooltip title check', async t=> { 
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const link1 = Selector('a').withExactText('Tooltips');
    const link2 = Selector('a').withExactText('ThemeRoller');
    const input = Selector('#age');
    await t.hover(link1);
    await t.expect(await link1.getAttribute('title')).notContains("That's what this widget is");
    await t.hover(link2);
    await t.expect(await link2.getAttribute('title')).notContains("ThemeRoller: jQuery UI's theme builder application");
    await t.hover(input);
    await t.expect(await input.getAttribute('title')).notContains("We ask for your age only for statistical purposes.");
})
test("Input type test" , async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const input = Selector('#age');
    await t.typeText(input,'21');
    await t.expect(await input.value).contains("21");
})

