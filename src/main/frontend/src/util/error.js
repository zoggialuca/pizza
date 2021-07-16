export function throwErrorIfResponseNotOk(response
    , error = Error(response && response.statusText ? response.statusText : "Unmanaged error")
    )
{
    if (!response.ok)
    {
        throw error;
    }
    return response;
}