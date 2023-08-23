We have test user. The task is to implement test that can be run against both Firefox and Google Chrome and follow the scenario:

[UI] Go to the https://vk.com/ webpage.
[UI] Authorize.
[UI] Go to "My profile".
[API] Create post on the wall with randomly generated text using API-request and save id of the post from the API-response.
[UI] Check that post with the sent text from the correct user appeared on the wall without refreshing the page.
[API] Edit the added post using API-request - change text and add (upload) a picture.
[UI] Check that text was updated and the picture was uploaded (make sure that pictures are the same) without refreshing the page.
[API] Add a comment to the post with the randomly generated text using API-request.
[UI] Check that comment from the correct user was added to the post without refreshing the page.
[UI] Like the post using UI.
[API] Check that the post received like from the correct user using API-request.
[API] Delete the post using API-request.
[UI] Check that the post was deleted without refreshing the page
