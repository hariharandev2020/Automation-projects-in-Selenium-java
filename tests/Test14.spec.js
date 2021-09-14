import { Selector} from 'testcafe';
import page from './method.po';

fixture `Test JQuery UI page`
.page `https://jqueryui.com/`

.beforeEach(async t => {
    await t.maximizeWindow()
    page.browserscroll();
    const tabs  = Selector('a').withExactText('Tabs');
    await t.click(tabs);
});
test('Tab1 Text', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const tab1    = Selector('a#ui-id-1');
    const tab2    = Selector('a#ui-id-2');
    const tab1txt = Selector('#tabs-1>p');
    const tab1p   = "Proin elit arcu, rutrum commodo, vehicula tempus, commodo a, risus. Curabitur nec arcu. Donec sollicitudin mi sit amet mauris. Nam elementum quam ullamcorper ante. Etiam aliquet massa et lorem. Mauris dapibus lacus auctor risus. Aenean tempor ullamcorper leo. Vivamus sed magna quis ligula eleifend adipiscing. Duis orci. Aliquam sodales tortor vitae ipsum. Aliquam nulla. Duis aliquam molestie erat. Ut et mauris vel pede varius sollicitudin. Sed ut dolor nec orci tincidunt interdum. Phasellus ipsum. Nunc tristique tempus lectus.";
    await t.click(tab2)
    .click(tab1);
    const txt  = await tab1.innerText; 
    const txt2 = await tab1txt.innerText; 
    await t.expect(txt).eql('Nunc tincidunt');
    await t.expect(txt2).eql(tab1p);   
})
test('Tab hover bg test', async t => {
    page.browserscroll();         
    await t.switchToIframe('.demo-frame');
    const tab1    = Selector('a#ui-id-1');
    const tab2    = Selector('a#ui-id-2');
    const tab3    = Selector('a#ui-id-3');
    const li1     = Selector('#tabs>ul>li').nth(0);
    const li2     = Selector('#tabs>ul>li').nth(1);
    const li3     = Selector('#tabs>ul>li').nth(2);
    await  t.hover(tab2)
    .hover(tab1)
    await t.expect(await li1.getAttribute('class')).contains('ui-state-hover')
    .hover(tab2)
    await t.expect(await li2.getAttribute('class')).contains('ui-state-hover')
    .hover(tab3)
    await t.expect(await li3.getAttribute('class')).contains('ui-state-hover');
})
test('Tab active bg test', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const tab1    = Selector('a#ui-id-1');
    const tab2    = Selector('a#ui-id-2');
    const tab3    = Selector('a#ui-id-3');
    const li1     = Selector('#tabs>ul>li').nth(0);
    const li2     = Selector('#tabs>ul>li').nth(1);
    const li3     = Selector('#tabs>ul>li').nth(2);
    await t.click(tab1)
    await t.expect(await li1.getStyleProperty('background-color')).eql('rgb(0, 127, 255)')
    .click(tab2)
    await t.expect(await li2.getStyleProperty('background-color')).eql('rgb(0, 127, 255)')
    .click(tab3)
    await t.expect(await li3.getStyleProperty('background-color')).eql('rgb(0, 127, 255)');
})

test('Tab2 Text', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const tab1    = Selector('a#ui-id-1');
    const tab2    = Selector('a#ui-id-2');
    const tab2txt = Selector('#tabs-2>p');
    const tab2p   = "Morbi tincidunt, dui sit amet facilisis feugiat, odio metus gravida ante, ut pharetra massa metus id nunc. Duis scelerisque molestie turpis. Sed fringilla, massa eget luctus malesuada, metus eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam. Praesent in eros vestibulum mi adipiscing adipiscing. Morbi facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut posuere viverra nulla. Aliquam erat volutpat. Pellentesque convallis. Maecenas feugiat, tellus pellentesque pretium posuere, felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris consectetur tortor et purus.";
    await t.click(tab1)
    .click(tab2);
    const txt =await tab2.innerText; 
    const txt2 = await tab2txt.innerText; 
    await t.expect(txt).eql('Proin dolor');
    await t.expect(txt2).eql(tab2p);   
})
test('Tab3 Text ', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const tab2    = Selector('a#ui-id-2');
    const tab3    = Selector('a#ui-id-3');
    const tab3txt = Selector('#tabs-3>p').nth(0);
    const tab3txt1 = Selector('#tabs-3>p').nth(1);
    await t.click(tab2)
    .click(tab3);
    const txt =await tab3.innerText; 
    const txt2 = await tab3txt.innerText;
    const txt3 = await tab3txt1.innerText; 
    const tab3p   = "Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.";
    const tab4p   = "Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.";
    await t.expect(txt).eql('Aenean lacinia');
    await t.expect(txt2).eql(tab3p);
    await t.expect(txt3).eql(tab4p);  
})