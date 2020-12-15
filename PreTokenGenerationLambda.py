def lambda_handler(event, context):
    # Specify space-delimited scopes
    # scopes = {"scope": "scope1 scope2"}
    # Or map Cognito Groups into scopes
    groups = event["request"]["groupConfiguration"]["groupsToOverride"]
    scopes = {"scope": " ".join(groups)}
    event['response']['claimsOverrideDetails'] = {'claimsToAddOrOverride': scopes}
    event['response']['claimsOverrideDetails']['claimsToSuppress'] = ['cognito:groups']
    return event