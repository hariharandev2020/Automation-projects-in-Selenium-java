import { Selector,ClientFunction } from 'testcafe';
import page from './method.po';

fixture `Test JQuery UI page`
.page `https://jqueryui.com/`

.beforeEach(async t => {
    await t.maximizeWindow()
    page.browserscroll();
    const menuBtn  = Selector('a').withExactText('Menu');
    await t.click(menuBtn);
});

test('Menu item count check', async t =>{
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    await t.expect(Selector('#menu>li').count).eql(7);
})
test('Menu item text check', async t =>{
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const list1    = Selector('#menu>li').nth(0);
    const list2    = Selector('li>#ui-id-2');
    const list3    = Selector('li>#ui-id-3');
    const list4    = Selector('li>#ui-id-4');
    const list8    = Selector('#ui-id-8');
    const list9    = Selector('#ui-id-9');
    const list18   = Selector('#menu>li').nth(6);
    await t.expect(await list1.getAttribute('class')).contains('ui-state-disabled');
    await t.expect(await list2.innerText).eql('Books');
    await t.expect(await list3.innerText).eql('Clothing');
    await t.expect(await list4.innerText).eql('Electronics');
    await t.expect(await list8.innerText).eql('Movies');
    await t.expect(await list9.innerText).eql('Music');
    await t.expect(await list18.getAttribute('class')).contains('ui-state-disabled');
})
test('Menu item text color check', async t =>{
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const list2    = Selector('li>#ui-id-2');
    const list3    = Selector('li>#ui-id-3');
    const list8    = Selector('#ui-id-8');
    await t.hover(list2);
    await t.expect(await list2.getStyleProperty('background-color')).eql('rgb(0, 127, 255)');
    await t.hover(list3);
    await t.expect(await list3.getStyleProperty('background-color')).eql('rgb(0, 127, 255)');
    await t.hover(list8);
    await t.expect(await list8.getStyleProperty('background-color')).eql('rgb(0, 127, 255)'); 
})
test('Menu item-4 check', async t =>{
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const ul1   = Selector('#menu > li:nth-child(4) > ul');
    const ul2   = Selector('#menu > li:nth-child(6) > ul');
    const ul3   = Selector('#menu > li:nth-child(6) > ul > li:nth-child(1) > ul');
    const ul4   = Selector('#menu > li:nth-child(6) > ul > li:nth-child(2) > ul');
    const list4    = Selector('#ui-id-4');
    const list5    = Selector('#ui-id-5');
    const list6    = Selector('#ui-id-6');
    const list7    = Selector('#ui-id-7');
    await t.expect(await ul1.getStyleProperty('display')).eql('none');
    await t.click(list4);
    await t.expect(await ul1.getStyleProperty('display')).eql('block');
    await t.expect(await Selector('#menu > li:nth-child(4) > ul>li').count).eql(3);
    await t.expect(await list5.innerText).eql('Home Entertainment');
    await t.expect(await Selector('#menu > li:nth-child(4) > ul > li.ui-state-disabled.ui-menu-item').getAttribute('class')).contains('ui-state-disabled');
    await t.expect(await (await list6.innerText)).eql('Car Hifi');
    await t.expect(await (await list7.innerText)).contains('Utilities');
    await t.hover(list6);
    await t.expect(await list6.getStyleProperty('background-color')).eql('rgb(0, 127, 255)');
    await t.click(list6);
    await t.expect(await ul1.getStyleProperty('display')).eql('none');
    await t.click(list4);
    await t.hover(list7);
    await t.expect(await ul1.getStyleProperty('display')).eql('block');
    await t.expect(await list7.getStyleProperty('background-color')).eql('rgb(0, 127, 255)');
    await t.click(list7);
    await t.expect(await ul1.getStyleProperty('display')).eql('none');
})
test('Menu item 8-1 test', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const list9    = Selector('#ui-id-9');
    const list10   = Selector('#ui-id-10');
    const list11   = Selector('#ui-id-11');
    const list12   = Selector('#ui-id-12');
    const ul2   = Selector('#menu > li:nth-child(6) > ul');
    const ul3   = Selector('#menu > li:nth-child(6) > ul '); 
    await t.expect(await ul2.getStyleProperty('display')).eql('none');
    await t.hover(list9);
    await t.expect(await ul2.getStyleProperty('display')).eql('block');
    await t.expect(await Selector('#menu > li:nth-child(6) > ul>li').count).eql(3);
    await t.expect(await ul3.getStyleProperty('display')).eql('block');
    await t.click(list9);
    await t.click(list10);
    await t.expect(await Selector(list10).getStyleProperty('background-color')).eql('rgb(0, 127, 255)');
    await t.expect(await ul2.getStyleProperty('display')).eql('block');
    await t.expect(await Selector('#menu > li:nth-child(6) > ul > li:nth-child(1) > ul>li').count).eql(2);
    await t.expect(await list11.innerText).eql('Alternative');
    await t.expect(await list12.innerText).eql('Classic');
})
test('Menu item 8-2 test', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const ul2   = Selector('#menu > li:nth-child(6) > ul');
    const ul3   = Selector('#menu > li:nth-child(6) > ul '); 
    const ul4   = Selector('#menu > li:nth-child(6) > ul > li:nth-child(2) > ul');
    const list9    = Selector('#ui-id-9');
    const list13   = Selector('#ui-id-13');
    const list14   = Selector('#ui-id-14');
    const list15   = Selector('#ui-id-15');
    const list16   = Selector('#ui-id-16')
    await t.expect(await ul2.getStyleProperty('display')).eql('none');
    await t.hover(list9);
    await t.expect(await ul2.getStyleProperty('display')).eql('block');
    await t.expect(await Selector('#menu > li:nth-child(6) > ul>li').count).eql(3);
    await t.expect(await ul4.getStyleProperty('display')).eql('none');
    await t.click(list9);
    await t.click(list13);
    await t.expect(await Selector(list13).getStyleProperty('background-color')).eql('rgb(0, 127, 255)');
    await t.expect(await ul2.getStyleProperty('display')).eql('block');
    await t.expect(await Selector('#menu > li:nth-child(6) > ul > li:nth-child(2) > ul>li').count).eql(3);
    await t.expect(await list14.innerText).eql('Freejazz');
    await t.expect(await list15.innerText).eql('Big Band');
    await t.expect(await list16.innerText).eql('Modern');
    await t.click(list16);
    await t.expect(await ul4.getStyleProperty('display')).eql('none');
    await t.expect(await ul3.getStyleProperty('display')).eql('none');
})
